package pro.jianbing.aboutme.controller.riding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.enums.EmailTypeEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.common.util.MailUtil;
import pro.jianbing.aboutme.service.CompanionService;
import pro.jianbing.aboutme.service.VisitService;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("riding/taihu")
public class TaihuController extends BaseController {

    private final VisitService visitService;
    private final CompanionService companionService;

    @Autowired
    public TaihuController(VisitService visitService, CompanionService companionService) {
        this.visitService = visitService;
        this.companionService = companionService;
    }

    @Autowired
    private MailUtil mailUtil;

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

    @ResponseBody
    @PostMapping("advise")
    public BaseResult update(@RequestParam("advise") String advise){
        BaseResult baseResult;
        try {
            logger.info("有人对你的环太湖骑行计划提了建议："+advise);
            mailUtil.sendMailTemplate(advise, EmailTypeEnum.RIDING_TAIHU_ADVISE);
            baseResult = BaseResult.success("建议已提出，并邮件通知他~~~");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.createBaseResult(false,"系统竟然出问题啦，可以微信告诉他(1700 520 9060)",null);
        }
        return baseResult;
    }
}
