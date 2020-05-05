package pro.jianbing.aboutme.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.jianbing.aboutme.common.enums.UrlEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.service.VisitService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common
 * @Author: jianbing
 * @CreateTime: 2020-5-5
 * @Description: ${Description}
 */
@Component
public class VisitCountCache {

    @Autowired
    private VisitService visitService;

    @PostConstruct
    public void init() {
        for (UrlEnum urlEnum : UrlEnum.values()) {
            GlobalObject.VISIT_COUNT.put(urlEnum.getCode(), visitService.getCountByTarget(urlEnum.getValue()));
        }
    }

    @PreDestroy
    public void destroy() {

    }

    @Scheduled(cron = "11 11 1 * * ?")
    public void refresh() {
        // 定时刷新缓存
        init();
    }
}
