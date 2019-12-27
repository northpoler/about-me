package pro.jianbing.aboutme.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import pro.jianbing.aboutme.pojo.WeatherDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private static final String SUCCESS = "\"status\":\"ok\"";
    /**
     * 获取天气的接口：此处的location可以有以下几种类型：
     * 1. 城市ID：城市列表
     * 2. 经纬度格式：经度,纬度（经度在前纬度在后，英文,分隔，十进制格式，北纬东经为正，南纬西经为负
     * 3. 城市名称，支持中英文和汉语拼音
     * 4. 城市名称，上级城市 或 省 或 国家，英文,分隔，此方式可以在重名的情况下只获取想要的地区的天气数据，例如 西安,陕西
     * 5. IP
     * 6. 根据请求自动判断，根据用户的请求获取IP，通过 IP 定位并获取城市数据
     * 示例数据如下：
     * 1. location=CN101010100
     * 2. location=116.40,39.9
     * 3. location=北京、 location=北京市、 location=beijing
     * 4. location=朝阳,北京、 location=chaoyang,beijing
     * 5. location=60.194.130.1
     * 6. location=auto_ip
     */
    private static final String LINK = "https://free-api.heweather.net/s6/weather/now";
    private static final String KEY = "8285153a6d83436193a6fe494cc028d2";
    private static final String URL = LINK + "?key=" + KEY + "&location=";
    /**
     * 获取天气
     *
     * @return
     */
    public static WeatherDto getWeather() {
        return getWeatherByIp("auto_ip");
    }

    /**
     * 获取天气
     *
     * @return
     */
    public static WeatherDto getWeatherByIp(String ip) {
        String url = URL + ip;
        String result = HttpUtil.doGet(url);
        WeatherDto dto = new WeatherDto();
        if (StringUtils.isEmpty(result) || !result.contains(SUCCESS)){
            return null;
        } else {
            Map resultMap = JSON.parseObject(result, Map.class);
            List<Map<String,Map<String,String>>> main = (List)resultMap.get("HeWeather6");
            Map<String, Map<String,String>> item = main.get(0);
            Map<String, String> basic = item.get("basic");
            dto.setProvince(basic.get("admin_area"));
            dto.setCity(basic.get("parent_city"));
            dto.setLongitude(basic.get("lon"));
            dto.setLatitude(basic.get("lat"));
            Map<String, String> update = item.get("update");
            dto.setPublishTime(update.get("loc").substring(11));
            Map<String, String> now = item.get("now");
            dto.setWeatherCode(now.get("cond_code"));
            dto.setWeather(now.get("cond_txt"));
            dto.setTemperature(now.get("tmp") + "℃");
            dto.setFeeling(now.get("fl") + "℃");
            dto.setVisibility(now.get("vis") + "公里");
            dto.setWindAngle(now.get("wind_deg") + "°");
            dto.setWindDirection(now.get("wind_dir"));
            dto.setWindForce(now.get("wind_sc") + "级");
            dto.setWindSpeed(now.get("wind_spd") + "公里/小时");
            dto.setCloud(now.get("cloud") + "(0-10)");
            dto.setRelativeHumidity(now.get("hum") + "%");
            dto.setPrecipitation(now.get("pcpn") + "mm");
            dto.setAtmos(now.get("pres") + "Pa");
            dto.setWeatherPic("../static/image/weather/"+dto.getWeatherCode()+".png");
        }
        return dto;
    }

    /**
     * 获取天气
     *
     * @return
     */
    public static WeatherDto getWeatherByRequest(HttpServletRequest request) {
        String ip = NetworkUtil.getIpAddress(request);
        return getWeatherByIp(ip);
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
