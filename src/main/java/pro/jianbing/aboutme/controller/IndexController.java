package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.pojo.po.User;
import pro.jianbing.aboutme.service.LikeService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    private final
    LikeService likeService;

    @Autowired
    public IndexController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/index")
    public String Index(Model model){
        int sumLikes = likeService.getSumLikes();
        model.addAttribute("likes",sumLikes);
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model){
        return "info";
    }
}
