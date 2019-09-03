package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.TimelineService;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
public class GrandpaController {

    private final LikeService likeService;
    private final TimelineService timelineService;
    @Autowired
    public GrandpaController(LikeService likeService, TimelineService timelineService) {
        this.likeService = likeService;
        this.timelineService = timelineService;
    }

    @GetMapping("grandpa")
    public String grandpa( Model model){
        int sumLikes = likeService.getSumLikes();
        List<Timeline> allNormalTimelines = timelineService.getAllNormalTimelines();
        model.addAttribute("lines",allNormalTimelines);
        model.addAttribute("likes",sumLikes);
        return "grandpa";
    }
}
