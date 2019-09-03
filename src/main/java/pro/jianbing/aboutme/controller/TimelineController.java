package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.TimelineService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
public class TimelineController {

    private final TimelineService service;

    @Autowired
    public TimelineController(TimelineService service) {
        this.service = service;
    }

    @GetMapping("timeline/add")
    public String add(){
        return "timeline_add";
    }

    @ResponseBody
    @PostMapping("timeline/insert")
    public Map<String,Object> insert(Timeline timeline, HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            timeline.setInsertTime(LocalDateTime.now());
            timeline.setMark("2");
            timeline.setIp(NetworkUtil.getIpAddress(request));
            Integer save = service.save(timeline);
            if (null != save && save>0){
                data.put("code",0);
                data.put("msg","提交成功");
            } else {
                data.put("code",500);
                data.put("msg","提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统出错");
        }
        return data;
    }
}
