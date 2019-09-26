package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public Map<String,Object> get(Model model,HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(4);
        try {
            User user = (User)request.getSession().getAttribute("user");
            String knowledge = knowledgeService.getByUserId(user.getId());
            model.addAttribute("knowledge",knowledge);
            data.put("data",knowledge);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("data","出错");
        }
        return data;
    }

    @PostMapping("save")
    @ResponseBody
    public Map<String,Object> save(Knowledge knowledge,HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(4);
        try {
            Integer result = knowledgeService.save(knowledge, request);
            data.put("code",result>0?0:1);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code",1);
        }
        return data;
    }
}
