package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.HouseMoney;

import java.util.List;


/**
 * @author DefaultAccount
 */
@Repository("houseMoneyRepository")
public interface HouseMoneyRepository extends JpaRepository<HouseMoney,Long> {
    List<HouseMoney> findAllByMarkOrderBySequenceAsc(String mark);
}
