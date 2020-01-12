package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.pojo.CountdownDto;
import pro.jianbing.aboutme.service.CountdownService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public BaseResult getUserCountdown(HttpServletRequest request){
        BaseResult baseResult;
        try {
            CountdownDto countdown = getCountdown(request);
            baseResult = BaseResult.success(countdown);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("edit")
    public String edit(HttpServletRequest request, Model model){
        model.addAttribute("countdownDto",getCountdown(request));
        return "countdown_edit";
    }

    @ResponseBody
    @PostMapping("update")
    public BaseResult update(CountdownDto countdownDto, HttpServletRequest request){
        BaseResult baseResult;
        try {
            Countdown countdown = new Countdown();
            countdown.setId(countdownDto.getId());
            countdown.setTitle(countdownDto.getTitle());
            String time = countdownDto.getTime();
            if (time.length()<8){
                time = time + ":00";
            }
            LocalDateTime dateTime = LocalDateTime
                    .parse(countdownDto.getDate()+" "+time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            countdown.setEndTime(dateTime);
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                countdown.setUserId(user.getId());
            }
            Integer save = countdownService.save(countdown);
            if (null != save && save>0){
                baseResult = BaseResult.success("编辑成功！");
            } else {
                baseResult = BaseResult.success("编辑失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    private CountdownDto getCountdown(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Countdown countdown = new Countdown();
        if (null != user){
            countdown.setUserId(user.getId());
        }
        return countdownService.getCountdown(countdown);
    }
}
