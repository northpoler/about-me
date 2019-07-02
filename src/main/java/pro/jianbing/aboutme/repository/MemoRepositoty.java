package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Memo;

import java.util.List;


/**
 * @author DefaultAccount
 */
@Repository("memoRepository")
public interface MemoRepositoty extends JpaRepository<Memo,Long> {
    /**
     * @param mark
     * @return
     */
    @Query
    List<Memo> findAllByMarkOrderBySequenceAsc(String mark);
}
