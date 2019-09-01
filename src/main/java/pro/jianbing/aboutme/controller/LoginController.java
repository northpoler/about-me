package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DefaultAccount
 */
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("logout")
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            request.getSession().removeAttribute("user");
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
    public Map<String,Object> checkLogin(User user, String rememberMe, HttpServletRequest request, HttpServletResponse response) {
        User result = userService.FindUserByUsername(user.getUsername());
        Map<String,Object> data = new HashMap<>(2);
        if (null != result && user.getPassword().equals(result.getPassword())){
            data.put("code",0);
            data.put("msg","登陆成功");
            request.getSession().setAttribute("user",result);
            /*Cookie cookie;
            if (null==rememberMe){
                cookie = new Cookie("remember_ticket","");
                cookie.setMaxAge(0);
            } else {
                cookie = new Cookie("remember_ticket", UUID.randomUUID().toString());
                cookie.setMaxAge(7*24*3600);
            }
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);*/
        } else {
            data.put("code",500);
            data.put("msg","用户名或密码错误");
        }
        return data;
    }
}
