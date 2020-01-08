package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.EncryptionUtil;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;
    private final LikeService likeService;
    @Value("${myself.password.salt}")
    private String salt;

    @Autowired
    public RegisterController(UserService userService, LikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("")
    public String login(Model model){
        model.addAttribute("staticSalt",salt);
        return "register";
    }

    @PostMapping("save")
    @ResponseBody
    public BaseResult checkLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        BaseResult baseResult;
        try {
            User result = userService.FindUserByUsername(user.getUsername());
            if (null == result){
                // 保存用户信息
                user.setRole("1");
                user.setMark("0");
                user.setCreated(LocalDateTime.now());
                String ipAddress = NetworkUtil.getIpAddress(request);
                user.setLastTime(LocalDateTime.now());
                user.setLastIP(ipAddress);
                int save = userService.saveUser(user);
                if (1==save){
                    request.getSession().setAttribute("user",user);
                    //将Session设置到Cookie中（留服务器判断浏览器是否登录使用！）
                    String loginToken = generateLoginToken(user.getUsername(), user.getPassword(), user.getId());
                    Cookie cookie = new Cookie("remember", loginToken);
                    //若我们这里不设置path，则只要访问“/login”时才会带该cooke
                    cookie.setPath("/");
                    cookie.setMaxAge(30*24*3600);
                    response.addCookie(cookie);
                    likeService.updateNullByUserIdAndIp(user.getId(),NetworkUtil.getIpAddress(request));
                    baseResult = BaseResult.success("注册成功,已自动登录!");
                } else {
                    baseResult = BaseResult.fail("注册出错！");
                }
            } else {
                baseResult = BaseResult.fail("此用户名已被注册！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    /**
     * 根据用户名、密码和用户id生成登录令牌
     * @param username 用户名
     * @param password 密码
     * @param userId 用户id
     * @return 登录令牌
     */
    private static String generateLoginToken(String username, String password, Long userId) {
        return EncryptionUtil.encrypt(username + ":" + password + ":" + userId);
    }
}
