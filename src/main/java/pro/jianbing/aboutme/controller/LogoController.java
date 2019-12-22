package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LogoService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/logo")
public class LogoController {

    private final
    LogoService logoService;

    @Autowired
    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @GetMapping("edit")
    public String editLogo(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        String logo;
        if (null!=user){
            logo = logoService.getLogoByUserId(user.getId());
        } else {
            logo = "../static/image/logo.png";
        }
        model.addAttribute("logo",logo);
        return "logo_edit";
    }

    @PostMapping("save")
    public Map<String,Object> saveLogo(String src, HttpServletRequest request){
        Map<String,Object> data = new HashMap<>(2);
        try {
            User user = (User)request.getSession().getAttribute("user");
            if (null != user){
                data.put("code",0);
                data.put("msg","添加成功");
            } else {
                data.put("code",500);
                data.put("msg","请先登录");
            }
        } catch (Exception e) {
            data.put("code",500);
            data.put("msg","系统出错");
            e.printStackTrace();
        }
        return data;
    }
}
