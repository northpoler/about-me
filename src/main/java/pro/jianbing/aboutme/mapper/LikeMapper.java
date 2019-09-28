package pro.jianbing.aboutme.mapper;

import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Like;

import java.util.List;
import java.util.Map;

@Repository
public interface LikeMapper {

    int getSumLikes();

    void insertLike(Like like);

    List<Map<String,Integer>> getCountLikesByUsers();
}
