package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.enums.EmailTypeEnum;
import pro.jianbing.aboutme.common.util.MailUtil;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.TimelineService;

import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("timeline")
public class TimelineController extends BaseController {

    private final TimelineService service;

    @Autowired
    public TimelineController(TimelineService service) {
        this.service = service;
    }

    @Autowired
    private MailUtil mailUtil;

    @GetMapping("add")
    public String add(){
        return "timeline_add";
    }

    @GetMapping("correct")
    public String correct(){
        return "timeline_correct";
    }

    @ResponseBody
    @PostMapping("insert")
    public BaseResult insert(Timeline timeline){
        BaseResult baseResult;
        try {
            timeline.setInsertTime(LocalDateTime.now());
            timeline.setIp(getIpByRequest());
            Integer save = service.save(timeline);
            if (null != save && save>0){
                mailUtil.sendMailTemplate(timeline.getContent(), EmailTypeEnum.TIMELINE);
                baseResult = BaseResult.success(ADD_SUCCESS);
            } else {
                baseResult = BaseResult.fail(ADD_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
