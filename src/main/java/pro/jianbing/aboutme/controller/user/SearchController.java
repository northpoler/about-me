package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.entity.Link;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LinkService;
import pro.jianbing.aboutme.service.LogoService;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
public class SearchController extends BaseController {

    private final
    LinkService linkService;
    private final
    LogoService logoService;

    @Autowired
    public SearchController(LinkService linkService, LogoService logoService) {
        this.linkService = linkService;
        this.logoService = logoService;
    }

    @GetMapping("")
    public String search(Model model){
        String domain = String.valueOf(getSession().getAttribute("domain"));
        if (null!=domain){
            List<Link> linkList = linkService.getLinkList(domain);
            model.addAttribute("links",linkList);
        }
        User user = getUser();
        String logo;
        if (null!=user){
            logo = logoService.getLogoByUserId(user.getId());
        } else {
            logo = "../static/image/logo_cat.png";
        }
        model.addAttribute("logo",logo);
        return "search";
    }
}
