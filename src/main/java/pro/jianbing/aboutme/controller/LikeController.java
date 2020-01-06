package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final
    LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("insert")
    public Integer insertLike(HttpServletRequest request){
        likeService.insertLike(request);
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
    public BaseResult getCountPersonalLikes(HttpServletRequest request){
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            int sumLikes = likeService.getSumPersonalLikes(user.getId());
            baseResult = BaseResult.success(sumLikes);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("count/personal/today")
    public BaseResult getCountPersonalLikesToday(HttpServletRequest request){
        BaseResult baseResult;
        try {
            User user = (User)request.getSession().getAttribute("user");
            int sumLikesToday = likeService.getSumPersonalLikesToday(user.getId());
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
