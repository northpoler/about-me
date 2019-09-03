package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Timeline;

import java.util.List;

/**
 * @author 李建兵
 */
@Repository("timelineRepository")
public interface TimelineRepositoty extends JpaRepository<Timeline,Long> {
    List<Timeline> findAllByMarkOrderBySequenceAsc(String s);

    List<Timeline> findAllByMarkIsNotContaining(String s);

}
