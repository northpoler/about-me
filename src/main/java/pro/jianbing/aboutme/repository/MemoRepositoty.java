package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Memo;

import javax.transaction.Transactional;
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

    /**
     * @param sequence
     * @return
     */
    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence+1 where sequence >= ?1")
    Integer increaseSequence(Long sequence);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence+1 where sequence >= ?1 and sequence < ?2")
    Integer increaseSequence(Long start, Long end);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence-1 where sequence >= ?1")
    Integer decreaseSequence(Long sequence);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence-1 where sequence > ?1 and sequence <= ?2")
    Integer decreaseSequence(Long start, Long end);
}
