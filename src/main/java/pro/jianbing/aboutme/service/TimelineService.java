package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.repository.TimelineRepositoty;

import javax.transaction.Transactional;

/**
 * @author 李建兵
 */
@Service
public class TimelineService {

    private final
    TimelineRepositoty timelineRepositoty;

    @Autowired
    public TimelineService(TimelineRepositoty timelineRepositoty) {
        this.timelineRepositoty = timelineRepositoty;
    }

    @Transactional
    public Integer save(Timeline timeline){
        Timeline save = timelineRepositoty.save(timeline);
        if (save!=null){
            return 1;
        }
        return 0;
    }

}
