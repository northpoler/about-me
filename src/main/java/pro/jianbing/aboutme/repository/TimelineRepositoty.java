package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Timeline;

/**
 * @author 李建兵
 */
@Repository("timelineRepository")
public interface TimelineRepositoty extends JpaRepository<Timeline,Long> {
    /**
     * 用户每次登陆后，更新IP和时间
     * @param lastIP
     * @param lastTime
     * @param id
     * @return
     */
    /*@Transactional
    @Modifying
    @Query("update User set last_IP = ?1 ,lastTime = ?2 where id = ?3")
    Integer updateLoginInfo(String lastIP, LocalDateTime lastTime, String id);*/
}
