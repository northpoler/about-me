package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LogoService;

import javax.servlet.http.HttpServletRequest;

@RestController
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
}
