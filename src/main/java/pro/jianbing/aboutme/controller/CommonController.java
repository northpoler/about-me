package pro.jianbing.aboutme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通用返回指定页面
 * @author DefaultAccount
 */
@Controller
public class CommonController {

    @GetMapping("unauthorized")
    public String unauthorized(){
        return "error/unauthorized";
    }
}
