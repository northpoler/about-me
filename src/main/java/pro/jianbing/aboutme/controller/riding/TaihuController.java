package pro.jianbing.aboutme.controller.riding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.enums.EmailTypeEnum;
import pro.jianbing.aboutme.common.enums.UrlEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.common.util.MailUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("riding/taihu")
public class TaihuController extends BaseController {

    @Autowired
    private MailUtil mailUtil;

    @GetMapping("")
    public String taihu(Model model) {
        try {
            model.addAttribute(GlobalString.TIMELINES, GlobalObject.HOT_DATA_MAP.get(GlobalString.TIMELINES));
            model.addAttribute("visitCounts", GlobalObject.VISIT_COUNT.get(UrlEnum.RIDING_TAIHU.getCode()));
            model.addAttribute("ridingTaihuNumber", GlobalObject.HOT_DATA_MAP.get(GlobalString.COMPANION_COUNT_TAIHU));
            int status;
            // 时间节点 0表示未开始 1表示进行中 2表示已结束
            LocalDate start = LocalDate.parse("2020-10-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse("2020-10-08", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (LocalDate.now().isBefore(start)) {
                status = 0;
            } else if (LocalDate.now().isBefore(end)) {
                status = 1;
            } else {
                status = 2;
            }
            model.addAttribute("status",status);
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
            baseResult = BaseResult.createBaseResult(false,"系统竟然出问题啦，可以微信/电话告诉他(1700 520 9060)",null);
        }
        return baseResult;
    }
}
