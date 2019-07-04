package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.entity.Link;

import java.util.List;


/**
 * @author DefaultAccount
 */
@Repository("linkRepository")
public interface LinkRepositoty extends JpaRepository<Link,Long> {
    /**
     * 获取所有的常用链接（升序，状态正常的）
     * @param mark
     * @return
     */
    @Query
    List<Link> findAllByMarkOrderBySequenceAsc(String mark);
}
