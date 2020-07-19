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
public interface MemoRepository extends JpaRepository<Memo,Long> {
    /**
     * @param userId
     * @return
     */
    @Query
    List<Memo> findAllByUserIdOrderBySequenceAsc(Long userId);

    /**
     * @param sequence
     * @return
     */
    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence+1 where sequence >= ?1 and userId = ?2")
    Integer increaseSequence(Long sequence, Long userId);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence+1 where sequence >= ?1 and sequence < ?2 and userId = ?3")
    Integer increaseSequence(Long start, Long end, Long userId);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence-1 where sequence >= ?1 and userId = ?2")
    Integer decreaseSequence(Long sequence, Long userId);

    @Transactional
    @Modifying
    @Query("update Memo set sequence = sequence-1 where sequence > ?1 and sequence <= ?2 and userId = ?3")
    Integer decreaseSequence(Long start, Long end, Long userId);

    long countByUserId(Long userId);
}
