package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.repository.TimelineRepositoty;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Timeline> getAllTimelines() {
        return timelineRepositoty.findAllByMarkIsNotContaining("1");
    }

    public List<Timeline> getAllNormalTimelines() {
        return timelineRepositoty.findAllByMarkOrderBySequenceAsc("0");
    }

    public Integer update(Timeline timeline,String field,String value) {
        Timeline line = timelineRepositoty.findById(timeline.getId()).get();
        if ("occurTime".equals(field)){
            line.setOccurTime(value);
        } else if ("content".equals(field)){
            line.setContent(value);
        } else if ("contributor".equals(field)){
            line.setContributor(value);
        } else if ("mark".equals(field)){
            line.setMark(value);
        } else if ("sequence".equals(field)){
            line.setSequence(Integer.parseInt(value));
        }
        Timeline save = timelineRepositoty.save(line);
        if (save!=null){
            return 1;
        }
        return 0;
    }
}