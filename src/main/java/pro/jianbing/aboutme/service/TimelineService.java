package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.cache.HotSpotDataCache;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.repository.TimelineRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 李建兵
 */
@Service
public class TimelineService {

    private final
    TimelineRepository timelineRepository;

    @Autowired
    public TimelineService(TimelineRepository timelineRepository) {
        this.timelineRepository = timelineRepository;
    }

    @Autowired
    private HotSpotDataCache hotSpotDataCache;

    private static void changeStyle(Timeline e) {
        e.setContent(e.getContent().replaceAll("--", "<br>"));
        e.setContent(e.getContent().replaceAll("//", "<span style='font-weight: bold'>"));
        e.setContent(e.getContent().replaceAll("##", "<span style='color: deeppink'>"));
        e.setContent(e.getContent().replaceAll("/#", "<span style='font-weight: bold;color: deeppink'>"));
        e.setContent(e.getContent().replaceAll("~~", "</span>"));
    }

    @Transactional
    public Integer save(Timeline timeline){
        Timeline save = timelineRepository.save(timeline);
        if (save!=null){
            return 1;
        }
        return 0;
    }

    public List<Timeline> getTimelinesByMark(String mark) {
        return timelineRepository.findAllByMarkOrderByOccurTimeAsc(mark);
    }

    public List<Timeline> getAllNormalTimelines() {
        List<Timeline> timelineList = getTimelinesByMark(GlobalString.MARK_NORMAL);
        timelineList.forEach(TimelineService::changeStyle);
        return timelineList;
    }

    public Integer update(Timeline timeline,String field,String value) {
        Timeline line = timelineRepository.findById(timeline.getId()).get();
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
        Timeline save = timelineRepository.save(line);
        if (GlobalString.MARK_NORMAL.equals(line.getMark())){
            new Thread(() -> hotSpotDataCache.initTimelines()).start();
        }
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
