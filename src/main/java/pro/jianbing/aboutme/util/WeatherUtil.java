package pro.jianbing.aboutme.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.jianbing.aboutme.pojo.WeatherDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 常用天气的工具
 *
 * @author 李建兵
 */
public final class WeatherUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherUtil.class);
    private static final String UNKNOWN_LOCATION = "unknown location";
    private static final String LOCATION = "location";

    /**
     * 通过request请求获取天气
     *
     * @param request 请求
     * @return
     * @throws IOException 输入输出流异常
     */
    public static WeatherDto getWeather(HttpServletRequest request) {
        // 通过请求，获取地址信息
        String ipAddress = NetworkUtil.getIpAddress(request);
        return getWeatherByIp(ipAddress);
    }

    /**
     * 通过IP获取天气
     *
     * @param ip
     * @return
     */
    public static WeatherDto getWeatherByIp(String ip) {
        String cityName = NetworkUtil.getCityNameByIp(ip);
        return getWeatherByCityName(cityName);
    }

    /**
     * 通过城市名获取天气
     * 接口：https://free-api.heweather.net/s6/weather/now?location=地区名称&key=3c3fa198cacc4152b94b20def11b2455
     *
     * @param cityName
     * @return
     */
    public static WeatherDto getWeatherByCityName(String cityName) {
        String url = "https://free-api.heweather.net/s6/weather/now?location="
                + cityName +
                "&key=3c3fa198cacc4152b94b20def11b2455";
        String result = HttpUtil.doGet(url);
        /*{"HeWeather6":[{"basic":{"cid":"CN101210101","location":"杭州","parent_city":"杭州","admin_area":"浙江",
                "cnty":"中国","lat":"30.28745842","lon":"120.15357971","tz":"+8.00"},"update":
            {"loc":"2019-12-26 20:15","utc":"2019-12-26 12:15"},"status":"ok","now":{"cloud":"91","" +
                    "cond_code":"101","cond_txt":"多云","fl":"1","hum":"73","pcpn":"0.0","pres":"1023",
                    "tmp":"6","vis":"16",
                "wind_deg":"353","wind_dir":"北风","wind_sc":"3","wind_spd":"19"}}]}*/
        /*{"HeWeather6":[{"basic":{"cid":"CN101010300","location":"朝阳","parent_city":"北京","admin_area":"北京",
                "cnty":"中国","lat":"39.92148972","lon":"116.48641205","tz":"+8.00"},
            "update":{"loc":"2019-12-26 20:00","utc":"2019-12-26 12:00"},"status":"ok",
                    "now":{"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"-3","hum":"32",
                    "pcpn":"0.0","pres":"1021","tmp":"0","vis":"16","wind_deg":"257","wind_dir":
                "西南风","wind_sc":"1","wind_spd":"4"}},{"basic":{"cid":"CN101071201","location":"朝阳",
                "parent_city":"朝阳","admin_area":"辽宁","cnty":"中国","lat":"41.57675934","lon":"120.4511795",
                "tz":"+8.00"},"update":{"loc":"2019-12-26 20:03","utc":"2019-12-26 12:03"},"status":"ok",
                "now":{"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"-13","hum":"61","pcpn":"0.0",
                "pres":"1003","tmp":"-10","vis":"16","wind_deg":"280","wind_dir":"西风","wind_sc":"1",
                "wind_spd":"3"}},{"basic":{"cid":"CN101060110","location":"朝阳","parent_city":"长春",
                "admin_area":"吉林","cnty":"中国","lat":"43.86491013","lon":"125.31803894","tz":"+8.00"},
            "update":{"loc":"2019-12-26 20:01","utc":"2019-12-26 12:01"},"status":"ok","now":{"cloud":"0",
                    "cond_code":"101","cond_txt":"多云","fl":"-25","hum":"89","pcpn":"0.0","pres":"995",
                    "tmp":"-21","vis":"10","wind_deg":"279","wind_dir":"西风","wind_sc":"1","wind_spd":"5"}}]}*/
        /*未知地区示例数据：{"HeWeather6":[{"status":"unknown location"}]}*/
        if (null == result || result.contains(UNKNOWN_LOCATION)) {
            // 发送邮件

            return null;
        } else {
            WeatherDto weatherDto = new WeatherDto();

            return weatherDto;
        }
    }

    /**
     * 通过城市代码获取天气
     * 接口：http://www.weather.com.cn/data/sk/101010100.html（101010100为城市代码，示例为北京市）
     *
     * @param cityCode
     * @return
     */
    public static WeatherDto getWeatherByCityCode(String cityCode) {
        return null;
    }
}
