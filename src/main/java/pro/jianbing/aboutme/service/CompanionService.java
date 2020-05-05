package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.cache.HotSpotDataCache;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Companion;
import pro.jianbing.aboutme.repository.CompanionRepositoty;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanionService {

    private final
    CompanionRepositoty companionRepositoty;
    @Autowired
    private HotSpotDataCache hotSpotDataCache;

    @Autowired
    public CompanionService(CompanionRepositoty companionRepositoty) {
        this.companionRepositoty = companionRepositoty;
    }

    public int countMembers() {
        return companionRepositoty.countCompanions();
    }

    public List<Companion> getListByMark(String mark) {
        return companionRepositoty.findAllByMarkOrderBySequenceAsc(mark);
    }

    @Transactional
    public Integer save(Companion companion){
        Companion save = companionRepositoty.save(companion);
        if (save!=null){
            hotSpotDataCache.initCompanionCount();
            return 1;
        }
        return 0;
    }

    public Integer update(Companion companion,String field,String value) {
        Companion dto = companionRepositoty.findById(companion.getId()).get();
        if ("name".equals(field)){
            dto.setName(value);
        } else if ("note".equals(field)){
            dto.setNote(value);
        } else if ("mark".equals(field)){
            dto.setMark(value);
        }
        Companion save = companionRepositoty.save(dto);
        if (!GlobalString.MARK_NORMAL.equals(dto.getMark())){
            hotSpotDataCache.initCompanionCount();
        }
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
