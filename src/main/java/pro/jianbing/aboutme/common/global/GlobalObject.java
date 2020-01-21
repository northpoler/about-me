package pro.jianbing.aboutme.common.global;

import java.util.Map;
import java.util.WeakHashMap;

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
    public final static Map<String, String> CONFIGURATION_MAP = new WeakHashMap<>();

    /**
     * 热点数据Map
     */
    public final static Map<String, Object> HOT_DATA_MAP = new WeakHashMap<>();
}
