package pro.jianbing.aboutme.pojo;

import lombok.Data;

/**
 * @author 李建兵
 */
@Data
public class WeatherDto {
    /**
     * 地区
     */
    private String location;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 最高气温
     */
    private String maxTemperature;
    /**
     * 最低气温
     */
    private String minTemperature;
    /**
     * 天气
     */
    private String weather;
}
