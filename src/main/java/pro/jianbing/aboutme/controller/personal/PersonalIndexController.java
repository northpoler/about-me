package pro.jianbing.aboutme.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author 李建兵
 */
@Controller
public class PersonalIndexController {
    @GetMapping("personal/index")
    public String personalIndex(HttpServletRequest request, Principal principal, Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (null == user){
            return "error/login";
        }
        String addressByIp = NetworkUtil.getAddressByIp(user.getLastIP());
        model.addAttribute("user",user);
        model.addAttribute("address",addressByIp);
        return "personal_index";
    }
}
