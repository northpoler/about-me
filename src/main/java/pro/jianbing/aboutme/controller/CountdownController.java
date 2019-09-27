package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.pojo.CountdownDto;
import pro.jianbing.aboutme.service.CountdownService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李建兵
 */
@RequestMapping("countdown")
@Controller
public class CountdownController {

    private final CountdownService countdownService;

    @Autowired
    public CountdownController(CountdownService countdownService) {
        this.countdownService = countdownService;
    }

    @ResponseBody
    @GetMapping("get")
    public Countdown getTwoCountdown(HttpServletRequest request){
        return getCountdown(request);
    }

    @GetMapping("edit")
    public String edit(HttpServletRequest request, Model model){
        Countdown countdown = getCountdown(request);
        CountdownDto countdownDto = new CountdownDto();
        countdownDto.setId(countdown.getId());
        countdownDto.setTitle(countdown.getTitle());
        countdownDto.setDate(countdown.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        countdownDto.setTime(countdown.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        model.addAttribute("countdownDto",countdownDto);
        return "countdown_edit";
    }

    @ResponseBody
    @PostMapping("update")
    public Map<String,Object> update(CountdownDto countdownDto, HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            Countdown countdown = new Countdown();
            countdown.setId(countdownDto.getId());
            countdown.setTitle(countdownDto.getTitle());
            LocalDateTime dateTime = LocalDateTime
                    .parse(countdownDto.getDate()+" "+countdownDto.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            countdown.setEndTime(dateTime);
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                countdown.setUserId(user.getId());
            }
            Integer save = countdownService.save(countdown);
            if (null != save && save>0){
                data.put("code",0);
                data.put("msg","编辑成功");
            } else {
                data.put("code",500);
                data.put("msg","编辑失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统出错");
        }
        return data;
    }

    private Countdown getCountdown(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Countdown countdown = new Countdown();
        if (null != user){
            countdown.setUserId(user.getId());
        }
        return countdownService.getCountdown(countdown);
    }
}
