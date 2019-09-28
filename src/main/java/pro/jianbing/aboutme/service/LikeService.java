package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Like;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.mapper.LikeMapper;
import pro.jianbing.aboutme.repository.LikeRepositoty;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LikeService{

    private final
    LikeMapper likeMapper;
    private final
    LikeRepositoty likeRepositoty;

    @Autowired
    public LikeService(LikeMapper likeMapper,LikeRepositoty likeRepositoty) {
        this.likeMapper = likeMapper;
        this.likeRepositoty = likeRepositoty;
    }

    public int getSumLikes() {
        return likeMapper.getSumLikes();
    }

    public Integer getSumLikesToday(){
        return likeRepositoty.countLikesToday();
    }

    public int getSumPersonalLikes(Long userId) {
        return likeRepositoty.getSumPersonalLikes(userId);
    }

    public Integer getSumPersonalLikesToday(Long userId){
        return likeRepositoty.countPersonalLikesToday(userId);
    }

    public void insertLike(HttpServletRequest request) {
        Like like = new Like();
        like.setId(UUID.randomUUID().toString().replaceAll("-",""));
        like.setIp(NetworkUtil.getIpAddress(request));
        like.setLikeTime(LocalDateTime.now());
        User user = (User) request.getSession().getAttribute("user");
        if (null!=user){
            like.setUserId(user.getId());
        }
        likeMapper.insertLike(like);
    }

    public List<Map<String,Integer>> getCountLikesByUsers() {
        return likeMapper.getCountLikesByUsers();
    }
}
