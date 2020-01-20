package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.Like;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController extends BaseController {

    private final
    LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("insert")
    public Integer insertLike(){
        Like like = new Like();
        like.setIp(getIpByRequest());
        User user = getUser();
        if (null!=user){
            like.setUserId(user.getId());
        }
        likeService.insertLike(like);
        return likeService.getSumLikes();
    }

    @GetMapping("count/all")
    public BaseResult getCountLikes(){
        BaseResult baseResult;
        try {
            int sumLikes = likeService.getSumLikes();
            baseResult = BaseResult.success(sumLikes);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("count/today")
    public BaseResult getCountLikesToday(){
        BaseResult baseResult;
        try {
            int sumLikesToday = likeService.getSumLikesToday();
            baseResult = BaseResult.success(sumLikesToday);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("count/personal/all")
    public BaseResult getCountPersonalLikes(){
        BaseResult baseResult;
        try {
            int sumLikes = likeService.getSumPersonalLikes(getUser().getId());
            baseResult = BaseResult.success(sumLikes);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("count/personal/today")
    public BaseResult getCountPersonalLikesToday(){
        BaseResult baseResult;
        try {
            int sumLikesToday = likeService.getSumPersonalLikesToday(getUser().getId());
            baseResult = BaseResult.success(sumLikesToday);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("count/users")
    public BaseResult getCountLikesByUsers(){
        BaseResult baseResult;
        try {
            List<Map<String,Integer>> countLikesByUsers = likeService.getCountLikesByUsers();
            baseResult = BaseResult.success(countLikesByUsers);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
