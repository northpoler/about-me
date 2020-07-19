package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Like;
import pro.jianbing.aboutme.mapper.LikeMapper;
import pro.jianbing.aboutme.repository.LikeRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LikeService{

    private final
    LikeMapper likeMapper;
    private final
    LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeMapper likeMapper,LikeRepository likeRepository) {
        this.likeMapper = likeMapper;
        this.likeRepository = likeRepository;
    }

    public int getSumLikes() {
        return likeMapper.getSumLikes();
    }

    public Integer getSumLikesToday(){
        return likeRepository.countLikesToday();
    }

    public int getSumPersonalLikes(Long userId) {
        return likeRepository.getSumPersonalLikes(userId);
    }

    public Integer getSumPersonalLikesToday(Long userId){
        return likeRepository.countPersonalLikesToday(userId);
    }

    public void insertLike(Like like) {
        like.setId(UUID.randomUUID().toString().replaceAll("-",""));
        like.setLikeTime(LocalDateTime.now());
        likeMapper.insertLike(like);
    }

    public List<Map<String,Integer>> getCountLikesByUsers() {
        return likeMapper.getCountLikesByUsers();
    }

    @Transactional
    public Integer updateNullByUserIdAndIp(Long userId, String ip){
        // 更新其他链接的排序
        Integer result = likeRepository.updateNullByUserIdAndIp(userId, ip);
        if (result > 0){
            return 1;
        }
        return 0;
    }
}
