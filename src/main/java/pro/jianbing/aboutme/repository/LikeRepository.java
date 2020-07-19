package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Like;

import javax.transaction.Transactional;

/**
 * @author 李建兵
 */
@Repository("likeRepository")
public interface LikeRepository extends JpaRepository<Like,String> {

    @Query("select count(like_time) from Like where like_time > current_date")
    Integer countLikesToday();

    @Query("select count(like_time) from Like where userId = ?1")
    Integer getSumPersonalLikes(Long userId);

    @Query("select count(like_time) from Like where like_time > current_date and userId = ?1")
    Integer countPersonalLikesToday(Long userId);

    @Transactional
    @Modifying
    @Query("update Like set userId = ?1 where userId is null and ip = ?2")
    Integer updateNullByUserIdAndIp(Long userId, String ip);
}
