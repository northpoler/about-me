package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.pojo.CountdownDto;
import pro.jianbing.aboutme.service.CountdownService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 李建兵
 */
@RequestMapping("countdown")
@Controller
public class CountdownController extends BaseController {

    private final CountdownService countdownService;

    @Autowired
    public CountdownController(CountdownService countdownService) {
        this.countdownService = countdownService;
    }

    @ResponseBody
    @GetMapping("get")
    public BaseResult getUserCountdown(){
        BaseResult baseResult;
        try {
            CountdownDto countdown = getCountdown();
            baseResult = BaseResult.success(countdown);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("edit")
    public String edit(Model model){
        model.addAttribute("countdownDto",getCountdown());
        return "countdown_edit";
    }

    @ResponseBody
    @PostMapping("update")
    public BaseResult update(CountdownDto countdownDto){
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
            User user = getUser();
            if (null!=user){
                countdown.setUserId(user.getId());
            }
            Integer save = countdownService.save(countdown);
            if (null != save && save>0){
                baseResult = BaseResult.success(MOD_SUCCESS);
            } else {
                baseResult = BaseResult.success(MOD_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    private CountdownDto getCountdown(){
        User user = getUser();
        Countdown countdown = new Countdown();
        if (null != user){
            countdown.setUserId(user.getId());
        }
        return countdownService.getCountdown(countdown);
    }
}
