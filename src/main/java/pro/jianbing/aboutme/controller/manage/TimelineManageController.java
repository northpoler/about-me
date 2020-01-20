package pro.jianbing.aboutme.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
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
@RequestMapping("manage/timeline")
public class TimelineManageController extends BaseController {

    private final TimelineService service;

    @Autowired
    public TimelineManageController(TimelineService service) {
        this.service = service;
    }

    @GetMapping("")
    public String manage(){
        User user = getUser();
        if (null == user){
            return "error/login";
        } else if (!"0".equals(user.getRole())){
            return "error/unauthorized";
        }
        return "manage/timeline";
    }

    @GetMapping("table/{mark}")
    @ResponseBody
    public Map<String,Object> getAllCountdown(@PathVariable String mark){
        Map<String,Object> data = null;
        try {
            List<Timeline> timelines = service.getTimelinesByMark(mark);
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
