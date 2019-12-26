package pro.jianbing.aboutme.test;

import pro.jianbing.aboutme.pojo.WeatherDto;
import pro.jianbing.aboutme.util.WeatherUtil;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.test
 * @Author: jianbing
 * @CreateTime: 2019-12-26 20:14
 * @Description: ${Description}
 */
public class WeatherUtilTest {
    public static void main(String[] args) {
        WeatherDto cityName = WeatherUtil.getWeatherByCityName("测试");
        System.out.println(cityName);
    }
}
