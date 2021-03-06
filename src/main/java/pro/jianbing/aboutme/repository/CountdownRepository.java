package pro.jianbing.aboutme.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Countdown;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author 李建兵
 */
@Repository("countdownRepository")
public interface CountdownRepository extends JpaRepository<Countdown,Long> {
    @Query("from Countdown where userId = :userId and endTime > :endTime order by endTime")
    List<Countdown> getByUserId(@Param("userId")Long userId,@Param("endTime") LocalDateTime endTime);
}
