package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.jianbing.aboutme.entity.Like;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.mapper.LikeMapper;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class LikeService{

    private final
    LikeMapper likeMapper;

    @Autowired
    public LikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    public int getSumLikes() {
        return likeMapper.getSumLikes();
    }

    public void insertLike(HttpServletRequest request) {
        Like like = new Like();
        like.setId(UUID.randomUUID().toString().replaceAll("-",""));
        like.setIp(NetworkUtil.getIpAddress(request));
        like.setLikeTime(new Timestamp(System.currentTimeMillis()));
        User user = (User) request.getSession().getAttribute("user");
        if (null!=user){
            like.setUserId(user.getId());
        }
        likeMapper.insertLike(like);
    }
}
