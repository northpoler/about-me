package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.MemoService;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
public class GrandpaController {

    private final LikeService likeService;
    @Autowired
    public GrandpaController(LikeService likeService, MemoService memoService) {
        this.likeService = likeService;
    }

    @GetMapping("grandpa")
    public String grandpa( Model model){
        int sumLikes = likeService.getSumLikes();
        model.addAttribute("likes",sumLikes);
        return "grandpa";
    }
}
