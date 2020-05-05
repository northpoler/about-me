package pro.jianbing.aboutme.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Companion;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.CompanionService;
import pro.jianbing.aboutme.service.TimelineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("manage/companion")
public class CompanionManageController extends BaseController {

    private final CompanionService service;

    @Autowired
    public CompanionManageController(CompanionService service) {
        this.service = service;
    }

    @GetMapping("")
    public String manage() {
        User user = getUser();
        if (null == user) {
            return "error/login";
        } else if (!"0".equals(user.getRole())) {
            return "error/unauthorized";
        }
        return "manage/companion";
    }

    @GetMapping("table")
    @ResponseBody
    public Map<String, Object> getAllNormalData() {
        Map<String, Object> data = null;
        try {
            List<Companion> companions = service.getListByMark(GlobalString.MARK_NORMAL);
            data = new HashMap<>(4);
            data.put("code", 0);
            data.put("msg", "success");
            data.put("data", companions);
            data.put("count", companions.size());
        } catch (Exception e) {
            e.printStackTrace();
            data.put("code", 500);
        }
        return data;
    }

    @PostMapping("update")
    @ResponseBody
    public BaseResult updateInfo(Companion companion, String field, String value) {
        BaseResult baseResult;
        try {
            Integer result = service.update(companion, field, value);
            baseResult = result > 0 ? BaseResult.success(MOD_SUCCESS) : BaseResult.fail(MOD_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @ResponseBody
    @PostMapping("add")
    public BaseResult add(@RequestParam("value") String value) {
        BaseResult baseResult;
        try {
            String[] split = value.split("##");
            if (split.length != 2) {
                baseResult = BaseResult.fail("信息请填写完整");
            } else {
                Companion companion = new Companion();
                companion.setName(split[0]);
                companion.setMark(GlobalString.MARK_NORMAL);
                companion.setNote(split[1]);
                service.save(companion);
                baseResult = BaseResult.success("操作成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
