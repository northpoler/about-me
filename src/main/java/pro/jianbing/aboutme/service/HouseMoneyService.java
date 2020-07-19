package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.HouseMoney;
import pro.jianbing.aboutme.repository.HouseMoneyRepository;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class HouseMoneyService {

    private final
    HouseMoneyRepository houseMoneyRepository;

    @Autowired
    public HouseMoneyService(HouseMoneyRepository houseMoneyRepository) {
        this.houseMoneyRepository = houseMoneyRepository;
    }

    public List<HouseMoney> getAll() {
        return houseMoneyRepository.findAllByMarkOrderBySequenceAsc(GlobalString.MARK_NORMAL);
    }
}
