package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.UserService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
public class TimelineController {

    private final UserService userService;

    @Autowired
    public TimelineController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("timeline/add")
    public String login(){
        return "timeline_add";
    }
}
