package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.UserService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String login(){
        return "register";
    }

    @PostMapping("save")
    @ResponseBody
    public Map<String,Object> checkLogin(User user, HttpServletRequest request) {
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
                    data.put("msg","注册成功");
                    request.getSession().setAttribute("user",user);
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
}
