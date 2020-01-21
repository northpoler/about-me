package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.MemoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("memo")
public class MemoController extends BaseController {
    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("")
    public String getMemoList(Model model){
        List<Memo> memoList = new ArrayList<>(5);
        User user = getUser();
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

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        User user = getUser();
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

    @DeleteMapping("/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id){
        BaseResult baseResult;
        try {
            User user = getUser();
            memoService.delete(id,user.getId());
            baseResult = BaseResult.success(DEL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResult insert(Memo memo){
        BaseResult baseResult;
        try {
            User user = getUser();
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
    @PostMapping("update/{id}")
    public BaseResult update(@PathVariable Long id, @ModelAttribute Memo memo) {
        BaseResult baseResult;
        try {
            User user = getUser();
            if (null!=user){
                memo.setUserId(user.getId());
            }
            memo.setId(id);
            Integer save = memoService.update(memo);
            baseResult = save>0? BaseResult.success(MOD_SUCCESS):BaseResult.fail(MOD_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
