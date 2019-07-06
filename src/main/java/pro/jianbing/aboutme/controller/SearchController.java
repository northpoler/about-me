package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.jianbing.aboutme.entity.Link;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.entity.Visit;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.service.LinkService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
public class SearchController {

    private final
    LinkService linkService;

    @Autowired
    public SearchController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("search")
    public String search(HttpServletRequest request,Model model){
        String domain = String.valueOf(request.getSession().getAttribute("domain"));
        if (null!=domain){
            List<Link> linkList = linkService.getLinkList(domain);
            model.addAttribute("links",linkList);
            return "search";
        }
        return "/";

    }
}
