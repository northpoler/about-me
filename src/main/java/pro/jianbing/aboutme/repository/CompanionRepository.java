package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Companion;

import java.util.List;

/**
 * @author 李建兵
 */
@Repository("companionRepository")
public interface CompanionRepository extends JpaRepository<Companion, Long> {
    @Query("select count(id) from Companion where mark = '0'")
    Integer countCompanions();

    List<Companion> findAllByMarkOrderBySequenceAsc(String s);
}
