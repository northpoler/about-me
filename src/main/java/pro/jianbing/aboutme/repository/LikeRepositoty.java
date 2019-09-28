package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Like;

import java.util.Map;

/**
 * @author 李建兵
 */
@Repository("likeRepository")
public interface LikeRepositoty extends JpaRepository<Like,String> {

    @Query("select count(like_time) from Like where like_time > current_date")
    Integer countLikesToday();

    @Query("select count(like_time) from Like where userId = ?1")
    Integer getSumPersonalLikes(Long userId);

    @Query("select count(like_time) from Like where like_time > current_date and userId = ?1")
    Integer countPersonalLikesToday(Long userId);
}
