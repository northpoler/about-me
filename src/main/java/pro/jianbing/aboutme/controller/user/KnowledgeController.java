package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.KnowledgeService;

import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@RequestMapping("knowledge")
@Controller
public class KnowledgeController extends BaseController {

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
    public BaseResult get(Model model){
        BaseResult baseResult;
        try {
            User user = getUser();
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
    public BaseResult save(Knowledge knowledge){
        BaseResult baseResult;
        try {
            knowledge.setIp(getIpByRequest());
            knowledge.setEditTime(LocalDateTime.now());
            knowledge.setUserId(getUser().getId());
            Integer result = knowledgeService.save(knowledge);
            baseResult = result>0?BaseResult.success(SAVE_SUCCESS):BaseResult.fail(SAVE_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
