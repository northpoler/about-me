package pro.jianbing.aboutme.pojo;

import lombok.Data;

/**
 * @author 李建兵
 */
@Data
public class WeatherDto {
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 气温
     */
    private String temperature;
    /**
     * 最高气温
     */
    private String maxTemperature;
    /**
     * 最低气温
     */
    private String minTemperature;
    /**
     * 天气代码
     */
    private String weatherCode;
    /**
     * 天气
     */
    private String weather;
    /**
     * 天气图片
     */
    private String weatherPic;
    /**
     * 云量
     */
    private String cloud;
    /**
     * 体感温度
     */
    private String feeling;
    /**
     * 相对湿度
     */
    private String relativeHumidity;
    /**
     * 降水量
     */
    private String precipitation;
    /**
     * 大气压强
     */
    private String atmos;
    /**
     * 能见度
     */
    private String visibility;
    /**
     * 风向角度
     */
    private String windAngle;
    /**
     * 风向
     */
    private String windDirection;
    /**
     * 风力
     */
    private String windForce;
    /**
     * 风速
     */
    private String windSpeed;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
}
