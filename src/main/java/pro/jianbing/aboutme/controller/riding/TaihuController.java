package pro.jianbing.aboutme.controller.riding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.service.CompanionService;
import pro.jianbing.aboutme.service.VisitService;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("riding/taihu")
public class TaihuController {

    private final VisitService visitService;
    private final CompanionService companionService;

    @Autowired
    public TaihuController(VisitService visitService, CompanionService companionService) {
        this.visitService = visitService;
        this.companionService = companionService;
    }

    @GetMapping("")
    public String taihu(Model model) {
        try {
            Long visitCounts = visitService.getCountByTarget("/riding/taihu");
            model.addAttribute(GlobalString.TIMELINES, GlobalObject.HOT_DATA_MAP.get(GlobalString.TIMELINES));
            model.addAttribute("visitCounts", visitCounts);
            model.addAttribute("ridingTaihuNumber", companionService.countMembers());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "riding/taihu";
    }
}
