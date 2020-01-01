package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.KnowledgeService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@RequestMapping("knowledge")
@Controller
public class KnowledgeController {

    private final
    KnowledgeService knowledgeService;

    @Autowired
    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("")
    public String knowledge(){
        return "knowledge";
    }

    @GetMapping("get")
    @ResponseBody
    public BaseResult get(Model model,HttpServletRequest request){
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            String knowledge = knowledgeService.getByUserId(user.getId());
            model.addAttribute("knowledge",knowledge);
            baseResult = BaseResult.success("",knowledge);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @PostMapping("save")
    @ResponseBody
    public BaseResult save(Knowledge knowledge,HttpServletRequest request){
        BaseResult baseResult;
        try {
            Integer result = knowledgeService.save(knowledge, request);
            baseResult = result>0?BaseResult.success("保存成功！"):BaseResult.fail("保存失败！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
