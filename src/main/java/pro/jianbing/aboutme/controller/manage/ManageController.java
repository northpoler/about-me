package pro.jianbing.aboutme.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("index")
    public String editLogo(){
        return "manage/index";
    }
}
