package pro.jianbing.aboutme.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Timeline;
import pro.jianbing.aboutme.service.CompanionService;
import pro.jianbing.aboutme.service.TimelineService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common
 * @Author: jianbing
 * @CreateTime: 2020-01-21 10:52
 * @Description: ${Description}
 */
@Component
public class HotSpotDataCache {

    @Autowired
    private TimelineService timelineService;
    @Autowired
    private CompanionService companionService;

    @PostConstruct
    public void init() {
        List<Timeline> timelines = timelineService.getAllNormalTimelines();
        int companionCounts = companionService.countMembers();
        GlobalObject.HOT_DATA_MAP.clear();
        GlobalObject.HOT_DATA_MAP.put(GlobalString.TIMELINES,timelines);
        GlobalObject.HOT_DATA_MAP.put(GlobalString.COMPANION_COUNT_TAIHU,companionCounts);
    }

    public void initCompanionCount(){
        GlobalObject.HOT_DATA_MAP.put(GlobalString.COMPANION_COUNT_TAIHU,companionService.countMembers());
    }

    public void initTimelines(){
        GlobalObject.HOT_DATA_MAP.put(GlobalString.TIMELINES,timelineService.getAllNormalTimelines());
    }

    @PreDestroy
    public void destroy() {

    }

    @Scheduled(cron = "22 22 2 * * ?")
    public void refresh() {
        // 定时刷新缓存
        init();
    }
}
