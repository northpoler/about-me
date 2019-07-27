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
public class IndexController {

    private final LikeService likeService;
    private final MemoService memoService;

    @Autowired
    public IndexController(LikeService likeService, MemoService memoService) {
        this.likeService = likeService;
        this.memoService = memoService;
    }

    @GetMapping("")
    public String index( Model model){
        int sumLikes = likeService.getSumLikes();
        model.addAttribute("likes",sumLikes);
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model){
        List<Memo> memoList = memoService.getMemoList();
        model.addAttribute("memos",memoList);
        return "info";
    }
}
