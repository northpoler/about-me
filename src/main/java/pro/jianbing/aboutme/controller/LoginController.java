package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.UserService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
public class LoginController {

    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    public LoginController(UserService userService, LikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("logout")
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> data = new HashMap<>(2);
        try {
            request.getSession().removeAttribute("user");
            Cookie cookie = new Cookie("remember", "");
            //若我们这里不设置path，则只要访问“/login”时才会带该cooke
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            data.put("code",0);
            data.put("msg","注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统异常");
        }
        return data;
    }

    @PostMapping("login/check")
    @ResponseBody
    public Map<String,Object> checkLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        User result = userService.FindUserByUsername(user.getUsername());
        Map<String,Object> data = new HashMap<>(2);
        if (null != result && user.getPassword().equals(result.getPassword())){
            // 保存登录信息
            String ipAddress = NetworkUtil.getIpAddress(request);
            userService.updateLoginInfo(ipAddress,result.getId());
            data.put("code",0);
            data.put("msg","登陆成功");
            request.getSession().setAttribute("user",result);
            //将Session设置到Cookie中（留服务器判断浏览器是否登录使用！）
            String loginToken = generateLoginToken(result.getUsername(), result.getPassword(), result.getId());
            Cookie cookie = new Cookie("remember", loginToken);
            //若我们这里不设置path，则只要访问“/login”时才会带该cooke
            cookie.setPath("/");
            cookie.setMaxAge(30*24*3600);
            response.addCookie(cookie);
            likeService.updateNullByUserIdAndIp(result.getId(),NetworkUtil.getIpAddress(request));
        } else {
            data.put("code",500);
            data.put("msg","用户名或密码错误");
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
        return username + ":" + password + ":" + userId;
    }
}
