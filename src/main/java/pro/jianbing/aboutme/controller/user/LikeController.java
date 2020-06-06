package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Like;
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
    public BaseResult insertLike(){
        BaseResult baseResult;
        try {
            Like like = new Like();
            like.setIp(getIpByRequest());
            if (null!=getUser()){
                like.setUserId(getUser().getId());
            }
            likeService.insertLike(like);
            int likes = (int)GlobalObject.HOT_DATA_MAP.get(GlobalString.LIKE_COUNT_GRANDPA);
            GlobalObject.HOT_DATA_MAP.put(GlobalString.LIKE_COUNT_GRANDPA,++likes);
            baseResult = BaseResult.success(likes);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
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
