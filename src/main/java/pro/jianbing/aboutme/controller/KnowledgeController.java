package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.service.KnowledgeService;
import pro.jianbing.aboutme.service.LikeService;

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
    public Map<String,Object> get(Model model){
        Map<String,Object> data = null;
        try {
            String knowledge = knowledgeService.get();
            model.addAttribute("knowledge",knowledge);
            data = new HashMap<>(4);
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
        Integer result = knowledgeService.save(knowledge, request);
        Map<String,Object> data = new HashMap<>(4);
        if (result>0){
            data.put("code",0);
        } else {
            data.put("code",1);
        }
        return data;
    }
}
