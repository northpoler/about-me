package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LogoService;

@Controller
@RequestMapping("/logo")
public class LogoController extends BaseController {

    private final
    LogoService logoService;

    @Autowired
    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @GetMapping("edit")
    public String editLogo(){
        return "logo_edit";
    }

    @PostMapping("save")
    public BaseResult saveLogo(String src){
        BaseResult baseResult;
        try {
            User user = getUser();
            baseResult = null == user? BaseResult.fail("请先登录！"):BaseResult.success("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
