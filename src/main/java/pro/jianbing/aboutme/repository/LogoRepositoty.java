package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Logo;

/**
 * @author 李建兵
 */
@Repository("logoRepository")
public interface LogoRepositoty extends JpaRepository<Logo,Long> {
    @Query("from Logo where userId = ?1 and mark = ?2")
    Logo findByUserIdAndMark(Long userId, String s);
}
