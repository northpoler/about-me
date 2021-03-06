package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Visit;


/**
 * @author 李建兵
 */
@Repository("visitRepository")
public interface VisitRepository extends JpaRepository<Visit,Long> {

    long countByTarget(String target);
}
