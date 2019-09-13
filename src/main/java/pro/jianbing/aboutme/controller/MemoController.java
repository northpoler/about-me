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
public class MemoController {
    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/info")
    public String info(Model model){
        List<Memo> memoList = memoService.getMemoList();
        model.addAttribute("memos",memoList);
        return "info";
    }
}
