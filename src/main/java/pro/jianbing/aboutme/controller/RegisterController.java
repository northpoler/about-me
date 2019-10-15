package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.UserService;
import pro.jianbing.aboutme.util.EncryptionUtil;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    public RegisterController(UserService userService, LikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("")
    public String login(){
        return "register";
    }

    @PostMapping("save")
    @ResponseBody
    public Map<String,Object> checkLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> data = null;
        try {
            User result = userService.FindUserByUsername(user.getUsername());
            data = new HashMap<>(2);
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
                    data.put("code",0);
                    data.put("msg","注册成功,已自动登录!");
                    request.getSession().setAttribute("user",user);
                    //将Session设置到Cookie中（留服务器判断浏览器是否登录使用！）
                    String loginToken = generateLoginToken(user.getUsername(), user.getPassword(), user.getId());
                    Cookie cookie = new Cookie("remember", loginToken);
                    //若我们这里不设置path，则只要访问“/login”时才会带该cooke
                    cookie.setPath("/");
                    cookie.setMaxAge(30*24*3600);
                    response.addCookie(cookie);
                    likeService.updateNullByUserIdAndIp(user.getId(),NetworkUtil.getIpAddress(request));
                } else {
                    data.put("code",500);
                    data.put("msg","注册出错");
                }
            } else {
                data.put("code",500);
                data.put("msg","此用户名已被注册");
            }
        } catch (Exception e) {
            data.put("code",500);
            data.put("msg","注册出错");
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 根据用户名、密码和用户id生成登录令牌
     * @param username 用户名
     * @param password 密码
     * @param userId 用户id
     * @return 登录令牌
     */
    public static String generateLoginToken(String username, String password, Long userId) {
        return EncryptionUtil.encrypt(username + ":" + password + ":" + userId);
    }
}
