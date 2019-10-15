package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("edit")
    public String edit(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "user_edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public Map<String,Object> update(User user, HttpServletRequest request){
        Map<String,Object> data = null;
        try {
            User userTemp = (User)request.getSession().getAttribute("user");
            User result = userService.FindUserByUsernameAndUserId(user.getUsername(),userTemp.getId());
            data = new HashMap<>(2);
            if (null == result){
                userTemp.setUsername(user.getUsername());
                userTemp.setPassword(user.getPassword());
                int save = userService.saveUser(userTemp);
                if (1==save){
                    data.put("code",0);
                    data.put("msg","修改成功,已自动登录!");
                    request.getSession().setAttribute("user",userTemp);
                } else {
                    data.put("code",500);
                    data.put("msg","修改出错");
                }
            } else {
                data.put("code",500);
                data.put("msg","此用户名已被注册");
            }
        } catch (Exception e) {
            data.put("code",500);
            data.put("msg","修改出错");
            e.printStackTrace();
        }
        return data;
    }
}
