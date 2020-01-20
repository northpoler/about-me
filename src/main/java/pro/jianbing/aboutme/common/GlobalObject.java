package pro.jianbing.aboutme.common;

import pro.jianbing.aboutme.common.dto.SystemConfiguration;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common
 * @Author: jianbing
 * @CreateTime: 2020-01-20 17:23
 * @Description: ${Description}
 */
public class GlobalObject {
    /**
     * 系统设置配置缓存Map
     */
    public final static Map<String, SystemConfiguration> CONFIGURATION_MAP = new WeakHashMap<>();
}
