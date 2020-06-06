package pro.jianbing.aboutme.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.common.enums.UrlEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("grandpa")
public class GrandpaController {

    @GetMapping("")
    public String grandpa(Model model){
        try {
            model.addAttribute(GlobalString.TIMELINES, GlobalObject.HOT_DATA_MAP.get(GlobalString.TIMELINES));
            model.addAttribute("likes",GlobalObject.HOT_DATA_MAP.get(GlobalString.LIKE_COUNT_GRANDPA));
            model.addAttribute("visitCounts",GlobalObject.VISIT_COUNT.get(UrlEnum.GRANDPA.getCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "grandpa";
    }
}
