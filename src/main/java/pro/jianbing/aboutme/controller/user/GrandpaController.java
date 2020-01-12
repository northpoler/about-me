package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.TimelineService;
import pro.jianbing.aboutme.service.VisitService;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("grandpa")
public class GrandpaController {

    private final LikeService likeService;
    private final TimelineService timelineService;
    private final VisitService visitService;
    @Autowired
    public GrandpaController(LikeService likeService, TimelineService timelineService, VisitService visitService) {
        this.likeService = likeService;
        this.timelineService = timelineService;
        this.visitService = visitService;
    }

    @GetMapping("")
    public String grandpa( Model model){
        try {
            int sumLikes = likeService.getSumLikes();
            List<Timeline> allNormalTimelines = timelineService.getAllNormalTimelines();
            Long visitCounts = visitService.getCountByTarget("/grandpa");
            model.addAttribute("lines",allNormalTimelines);
            model.addAttribute("likes",sumLikes);
            model.addAttribute("visitCounts",visitCounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "grandpa";
    }
}
