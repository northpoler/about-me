package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Visit;
import pro.jianbing.aboutme.repository.VisitRepository;

/**
 * @author 李建兵
 */
@Service
public class VisitService {

    private final
    VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional
    public Integer saveVisit(Visit visit){
        visit = visitRepository.save(visit);
        if (visit!=null){
            return 1;
        }
        return 0;
    }

    public Long getCountByTarget(String target) {
        return visitRepository.countByTarget(target);
    }
}
