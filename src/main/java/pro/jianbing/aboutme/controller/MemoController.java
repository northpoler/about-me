package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.MemoService;

import javax.servlet.http.HttpServletRequest;
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
    public String getMemoList(Model model){
        List<Memo> memoList = memoService.getMemoList();
        model.addAttribute("memos",memoList);
        return "memo";
    }

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("maxSequence",memoService.count()+1);
        return "memo_add";
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

    @ResponseBody
    @PostMapping("")
    public Map<String,Object> insert(Memo memo, HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                memo.setUserId(user.getId());
            }
            Integer save = memoService.save(memo);
            if (null != save && save>0){
                data.put("code",0);
                data.put("msg","提交成功");
            } else {
                data.put("code",500);
                data.put("msg","提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统出错");
        }
        return data;
    }
}
