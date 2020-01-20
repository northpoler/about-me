package pro.jianbing.aboutme.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.User;

/**
 * @author 李建兵
 */
@Controller
public class PersonalIndexController extends BaseController {
    @GetMapping("personal/index")
    public String personalIndex(Model model){
        User user = getUser();
        if (null == user){
            return "error/login";
        }
        String addressByIp = NetworkUtil.getAddressByIp(user.getLastIP());
        model.addAttribute("user",user);
        model.addAttribute("address",addressByIp);
        return "personal_index";
    }
}
