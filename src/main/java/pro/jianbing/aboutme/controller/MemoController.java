package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.service.MemoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("memo")
public class MemoController {
    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("")
    public String info(Model model){
        List<Memo> memoList = memoService.getMemoList();
        model.addAttribute("memos",memoList);
        return "memo";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delete(@PathVariable Long id){
        Map<String,Object> data = new HashMap<>(2);
        try {
            memoService.delete(id);
            data.put("code",0);
            data.put("msg","删除成功");
        } catch (Exception e) {
            data.put("code",500);
            data.put("msg","操作失败");
            e.printStackTrace();
        }
        return data;
    }
}
