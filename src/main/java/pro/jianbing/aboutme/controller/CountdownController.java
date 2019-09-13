package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.CountdownService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李建兵
 */
@RequestMapping("countdown")
@RestController
public class CountdownController {

    private final CountdownService countdownService;

    @Autowired
    public CountdownController(CountdownService countdownService) {
        this.countdownService = countdownService;
    }

    @GetMapping("get")
    public List<Countdown> getTwoCountdown(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Countdown countdown = new Countdown();
        if (null != user){
            countdown.setUserId(user.getId());
        }
        countdown.setEndTime(LocalDateTime.now());
        return countdownService.getTwoCountdown(countdown);
    }
}
