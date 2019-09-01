package pro.jianbing.aboutme.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Countdown;

import java.util.List;


/**
 * @author 李建兵
 */
@Repository("countdownRepository")
public interface CountdownRepositoty extends JpaRepository<Countdown,Long> {
    @Query("from Countdown where userId = :userId")
    List<Countdown> getTwoByUserId(@Param("userId")String userId);
    @Query("from Countdown where userId is null")
    List<Countdown> getTwoWithoutUser();
}
