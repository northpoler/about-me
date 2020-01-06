package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.MemoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id,Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("maxSequence",memoService.getMaxSequence(user.getId())+1);
        Memo old;
        // id小于0表示添加
        if (id>0){
            old = memoService.getOld(id);
        } else {
            old = new Memo();
        }
        model.addAttribute("memo",old);
        return "memo_edit";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult delete(@PathVariable Long id, HttpServletRequest request){
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            memoService.delete(id,user.getId());
            baseResult = BaseResult.success("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResult insert(Memo memo, HttpServletRequest request){
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                memo.setUserId(user.getId());
            }
            Integer save = memoService.save(memo);
            baseResult = save>0? BaseResult.success("添加成功！"):BaseResult.fail("添加失败！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public BaseResult update(@PathVariable Long id, @ModelAttribute Memo memo, HttpServletRequest request) {
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            if (null!=user){
                memo.setUserId(user.getId());
            }
            memo.setId(id);
            Integer save = memoService.update(memo);
            baseResult = save>0? BaseResult.success("更新成功！"):BaseResult.fail("更新失败！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
