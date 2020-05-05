package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.common.enums.UrlEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.service.LikeService;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("grandpa")
public class GrandpaController {

    private final LikeService likeService;
    @Autowired
    public GrandpaController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("")
    public String grandpa(Model model){
        try {
            int sumLikes = likeService.getSumLikes();
            model.addAttribute(GlobalString.TIMELINES, GlobalObject.HOT_DATA_MAP.get(GlobalString.TIMELINES));
            model.addAttribute("likes",sumLikes);
            model.addAttribute("visitCounts",GlobalObject.VISIT_COUNT.get(UrlEnum.GRANDPA.getCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "grandpa";
    }
}
