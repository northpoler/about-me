package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Like;

/**
 * @author 李建兵
 */
@Repository("companionRepository")
public interface CompanionRepositoty extends JpaRepository<Like, String> {
    @Query("select count(id) from Companion where mark = '0'")
    Integer countCompanions();
}
