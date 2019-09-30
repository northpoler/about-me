package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.MemoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public String getMemoList(Model model, HttpServletRequest request){
        List<Memo> memoList = new ArrayList<>(5);
        User user = (User)request.getSession().getAttribute("user");
        if (null!=user){
            memoList = memoService.getMemoList(user.getId());
        }
        if (memoList.size()==0){
            Memo memo = new Memo("百度","https://www.baidu.com",1L,"0");
            memoList.add(memo);
            if (null!=user){
                memo.setUserId(user.getId());
                memoService.save(memo);
            }
        }
        model.addAttribute("memos",memoList);
        return "memo";
    }

    @GetMapping("add")
    public String add(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("maxSequence",memoService.getMaxSequence(user.getId())+1);
        return "memo_add";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id,Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("maxSequence",memoService.getMaxSequence(user.getId())+1);
        model.addAttribute("memo",memoService.getOld(id));
        return "memo_edit";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delete(@PathVariable Long id,HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            User user = (User)request.getSession().getAttribute("user");
            memoService.delete(id,user.getId());
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
                data.put("msg","添加成功");
            } else {
                data.put("code",500);
                data.put("msg","添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统出错");
        }
        return data;
    }

    @ResponseBody
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public Map<String,Object> update(@PathVariable Long id, @ModelAttribute Memo memo, HttpServletRequest request) {
        Map<String,Object> data = new HashMap<>(2);
        try {
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                memo.setUserId(user.getId());
            }
            memo.setId(id);
            Integer save = memoService.update(memo);
            if (null != save && save>0){
                data.put("code",0);
                data.put("msg","更新成功");
            } else {
                data.put("code",500);
                data.put("msg","更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",500);
            data.put("msg","系统出错");
        }
        return data;
    }
}
