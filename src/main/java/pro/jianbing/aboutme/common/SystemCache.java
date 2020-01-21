package pro.jianbing.aboutme.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.jianbing.aboutme.common.dto.SystemConfiguration;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.service.system.SystemConfigurationService;

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
public class SystemCache {

    @Autowired
    private SystemConfigurationService configurationService;

    @PostConstruct
    public void init() {
        // 把系统配置存放缓存中
        List<SystemConfiguration> configurations = configurationService.getAll();
        GlobalObject.CONFIGURATION_MAP.clear();
        configurations.forEach(e -> GlobalObject.CONFIGURATION_MAP.put(e.getItem(),e.getValue()));
    }

    @PreDestroy
    public void destroy() {

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void refresh() {
        // 定时刷新缓存
        init();
    }
}
