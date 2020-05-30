package pro.jianbing.aboutme.common.global;

import net.jodah.expiringmap.ExpiringMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common
 * @Author: jianbing
 * @CreateTime: 2020-01-20 17:23
 * @Description: 全局对象
 */
public class GlobalObject {
    /**
     * 系统设置配置缓存Map
     */
    public final static Map<String, String> CONFIGURATION_MAP = new HashMap<>();

    /**
     * 热点数据Map
     */
    public final static Map<String, Object> HOT_DATA_MAP = new HashMap<>();

    /**
     * 访问量Map
     */
    public final static Map<String, Long> VISIT_COUNT = new HashMap<>();

    /**
     * 页面心跳Map
     */
    public final static Map<String, ExpiringMap<String, String>> PAGE_HEARTBEAT = new HashMap<>();
}
