package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.MailUtil;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.TimelineService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("timeline")
public class TimelineController {

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
    public BaseResult insert(Timeline timeline, HttpServletRequest request){
        BaseResult baseResult;
        try {
            timeline.setInsertTime(LocalDateTime.now());
            timeline.setIp(NetworkUtil.getIpAddress(request));
            Integer save = service.save(timeline);
            if (null != save && save>0){
                mailUtil.sendMailTemplate(timeline.getContent());
                baseResult = BaseResult.success("提交成功！");
            } else {
                baseResult = BaseResult.fail("提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("manage")
    public String manage(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (null == user){
            return "error/login";
        } else if (!"0".equals(user.getRole())){
            return "error/unauthorized";
        }
        return "timeline_manage";
    }

    @GetMapping("table")
    @ResponseBody
    public Map<String,Object> getAllCountdown(){
        Map<String,Object> data = null;
        try {
            List<Timeline> timelines = service.getAllTimelines();
            data = new HashMap<>(4);
            data.put("code",0);
            data.put("msg","success");
            data.put("data",timelines);
            data.put("count",timelines.size());
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
        }
        return data;
    }

    @PostMapping("update")
    @ResponseBody
    public BaseResult updateInfo(Timeline timeline,String field,String value){
        BaseResult baseResult;
        try {
            Integer result = service.update(timeline,field,value);
            baseResult = result > 0?BaseResult.success("修改成功！"):BaseResult.fail("修改失败！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
