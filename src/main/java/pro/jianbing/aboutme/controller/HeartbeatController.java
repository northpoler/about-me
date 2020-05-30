package pro.jianbing.aboutme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.jianbing.aboutme.common.cache.HeartbeatCache;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Heartbeat;
import pro.jianbing.aboutme.entity.User;

/**
 * @author jianbing
 */
@Controller
@RequestMapping("heartbeat")
public class HeartbeatController extends BaseController {

    @GetMapping("")
    @ResponseBody
    public BaseResult heartbeat(Heartbeat heartbeat) {
        BaseResult baseResult;
        try {
            HeartbeatCache.add(heartbeat);
            User user = getUser();
            if (null != user && "0".equals(user.getRole())) {
                int visitCount = HeartbeatCache.getVisitCount(heartbeat.getPageName());
                baseResult = BaseResult.success(visitCount);
            } else {
                baseResult = BaseResult.success();
            }
        } catch (Exception e) {
            baseResult = BaseResult.systemError();
            e.printStackTrace();
        }
        return baseResult;
    }
}
