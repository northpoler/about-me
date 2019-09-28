package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.LikeService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public Map<String,Object> getCountLikes(){
        int sumLikes = likeService.getSumLikes();
        Map<String, Object> result = getResult(sumLikes);
        return result;
    }

    @GetMapping("count/today")
    public Map<String,Object> getCountLikesToday(){
        int sumLikesToday = likeService.getSumLikesToday();
        Map<String, Object> result = getResult(sumLikesToday);
        return result;
    }

    @GetMapping("count/personal/all")
    public Map<String,Object> getCountPersonalLikes(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        int sumLikes = likeService.getSumPersonalLikes(user.getId());
        Map<String, Object> result = getResult(sumLikes);
        return result;
    }

    @GetMapping("count/personal/today")
    public Map<String,Object> getCountPersonalLikesToday(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        int sumLikesToday = likeService.getSumPersonalLikesToday(user.getId());
        Map<String, Object> result = getResult(sumLikesToday);
        return result;
    }

    @GetMapping("count/users")
    public Map<String,Object> getCountLikesByUsers(){
        Map<String,Object> result = new HashMap<>(2);
        List<Map<String,Integer>> countLikesByUsers = likeService.getCountLikesByUsers();
        result.put("code",0);
        result.put("data",countLikesByUsers);
        return result;
    }

    private Map<String,Object> getResult(Integer data){
        Map<String,Object> result = new HashMap<>(2);
        if (data>0){
            result.put("code",0);
            result.put("data",data);
        } else {
            result.put("code",1);
        }
        return result;
    }
}
