package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.EncryptionUtil;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author DefaultAccount
 */
@Controller
public class LoginController extends BaseController {

    private final UserService userService;
    private final LikeService likeService;
    @Value("${myself.password.salt}")
    private String salt;

    @Autowired
    public LoginController(UserService userService, LikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("login")
    public String login(Model model){
        String variantSalt = UUID.randomUUID().toString();
        getSession().setAttribute("variantSalt",variantSalt);
        model.addAttribute("variantSalt", variantSalt);
        model.addAttribute("staticSalt",salt);
        return "login";
    }

    @GetMapping("logout")
    @ResponseBody
    public BaseResult logout(HttpServletResponse response){
        BaseResult baseResult;
        try {
            getSession().removeAttribute("user");
            Cookie cookie = new Cookie("remember", "");
            //若我们这里不设置path，则只要访问“/login”时才会带该cooke
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            baseResult = BaseResult.success("注销成功！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @PostMapping("login/check")
    @ResponseBody
    public BaseResult checkLogin(User user, HttpServletResponse response) {
        BaseResult baseResult;
        try {
            User result = userService.FindUserByUsername(user.getUsername());
            String variantSalt = (String)getSession().getAttribute("variantSalt");
            if (StringUtils.isEmpty(variantSalt)){
                baseResult = BaseResult.fail("长时间未操作，请刷新网页后重新登陆");
            } else if (null == result) {
                baseResult = BaseResult.fail("用户名或密码错误！");
            } else {
                String password = DigestUtils.md5DigestAsHex((result.getPassword() + variantSalt).getBytes());
                if (user.getPassword().equals(password)){
                    // 保存登录信息
                    String ipAddress = getIpByRequest();
                    userService.updateLoginInfo(ipAddress,result.getId());
                    getSession().setAttribute("user",result);
                    //将Session设置到Cookie中（留服务器判断浏览器是否登录使用！）
                    String loginToken = generateLoginToken(result.getUsername(), result.getPassword(), result.getId());
                    Cookie cookie = new Cookie("remember", loginToken);
                    //若我们这里不设置path，则只要访问“/login”时才会带该cooke
                    cookie.setPath("/");
                    cookie.setMaxAge(30*24*3600);
                    response.addCookie(cookie);
                    likeService.updateNullByUserIdAndIp(result.getId(),getIpByRequest());
                    getSession().removeAttribute("variantSalt");
                    baseResult = BaseResult.success("登录成功！");
                } else {
                    baseResult = BaseResult.fail("用户名或密码错误！");
                }
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
