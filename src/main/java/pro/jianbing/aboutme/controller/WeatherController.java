package pro.jianbing.aboutme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.pojo.WeatherDto;
import pro.jianbing.aboutme.util.WeatherUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("weather")
public class WeatherController {

    @PostMapping("/get")
    @ResponseBody
    public Map<String,Object> getWeather(String ip){
        Map<String,Object> data = new HashMap<>(2);
        try {
            WeatherDto weather = WeatherUtil.getWeatherByRequest(request);
            /*
             * 上面为正式语句，下面是测试用
             */
            /*WeatherDto weather = WeatherUtil.getWeatherByIp("auto_ip");*/
            if (null!=weather){
                data.put("result",true);
                data.put("weather",weather);
            } else {
                data.put("result",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("result",false);
        }
        return data;
    }
}
