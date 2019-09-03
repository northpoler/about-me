package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.TimelineService;
import pro.jianbing.aboutme.util.NetworkUtil;

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

    @GetMapping("add")
    public String add(){
        return "timeline_add";
    }

    @ResponseBody
    @PostMapping("insert")
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

    @GetMapping("manage")
    public String manage(){
        return "timeline_manage";
    }

    /*@GetMapping("add")
    public String addCountdownPage(){
        return "add_countdown";
    }

    @PostMapping("add")
    @ResponseBody
    public Map<String,Object> addCountdown(CountdownDto countdownDto){
        Integer result = countdownService.addCountdownInfo(countdownDto);
        Map<String,Object> data = new HashMap<>(4);
        if (result>0){
            data.put("code",0);
        } else {
            data.put("code",1);
        }
        return data;
    }*/

    @GetMapping("table")
    @ResponseBody
    public Map<String,Object> getTwoCountdown(){
        List<Timeline> timelines = service.getAllTimelines();
        Map<String,Object> data = new HashMap<>(4);
        data.put("code",0);
        data.put("msg","success");
        data.put("data",timelines);
        data.put("count",data.size());
        return data;
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String,Object> updateInfo(Timeline timeline,String field,String value){
        Integer result = service.update(timeline,field,value);
        Map<String,Object> data = new HashMap<>(4);
        if (result>0){
            data.put("code",0);
        } else {
            data.put("code",1);
        }
        return data;
    }

    /*@PostMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteInfo(CountdownDto countdownDto){
        Integer result = countdownService.deleteCountdownInfo(countdownDto);
        Map<String,Object> data = new HashMap<>(4);
        if (result>0){
            data.put("code",0);
        } else {
            data.put("code",1);
        }
        return data;
    }*/
}
