package pro.jianbing.aboutme.common.cache;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.entity.Heartbeat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common
 * @Author: jianbing
 * @CreateTime: 2020-01-21 10:52
 * @Description: ${Description}
 */
@Component
public class HeartbeatCache {

    /**
     * 心跳时间跨度
     */
    private final static long TIME_STEP = 10000L;

    @PostConstruct
    public void init() {
        GlobalObject.PAGE_HEARTBEAT.clear();
    }

    @PreDestroy
    public void destroy() {

    }

    @Scheduled(cron = "33 33 3 * * ?")
    public void refresh() {
        // 定时清除超期数据
        /*init();*/
    }

    public static void add(Heartbeat heartbeat) {
        ExpiringMap<String, String> map = GlobalObject.PAGE_HEARTBEAT.get(heartbeat.getPageName());
        if (null == map) {
            map = ExpiringMap.builder().expiration(TIME_STEP, TimeUnit.MILLISECONDS)
                    .expirationPolicy(ExpirationPolicy.CREATED).build();
            GlobalObject.PAGE_HEARTBEAT.put(heartbeat.getPageName(), map);
        }
        map.put(heartbeat.getClientId(), heartbeat.getSendTime());
    }

    public static int getVisitCount(String pageName) {
        return GlobalObject.PAGE_HEARTBEAT.get(pageName).size();
    }
}
