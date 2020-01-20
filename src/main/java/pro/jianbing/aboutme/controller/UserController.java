package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("edit")
    public String edit(Model model){
        User user = getUser();
        model.addAttribute("user",user);
        return "user_edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public BaseResult update(User user){
        BaseResult baseResult;
        try {
            User userTemp = getUser();
            User result = userService.FindUserByUsernameAndUserId(user.getUsername(),userTemp.getId());
            if (null == result){
                userTemp.setUsername(user.getUsername());
                userTemp.setPassword(user.getPassword());
                int save = userService.saveUser(userTemp);
                if (1==save){
                    baseResult = BaseResult.success("修改成功,已自动登录!");
                    getSession().setAttribute("user",userTemp);
                } else {
                    baseResult = BaseResult.fail("修改出错！");
                }
            } else {
                baseResult = BaseResult.fail("此用户名已被注册！");
            }
        } catch (Exception e) {
            baseResult = BaseResult.systemError();
            e.printStackTrace();
        }
        return baseResult;
    }
}
