package pro.jianbing.aboutme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.WeatherUtil;
import pro.jianbing.aboutme.pojo.WeatherDto;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("weather")
public class WeatherController {

    @PostMapping("/get")
    @ResponseBody
    public BaseResult getWeather(String ip){
        BaseResult baseResult;
        try {
            WeatherDto weather = WeatherUtil.getWeatherByIp(ip);
            baseResult = null==weather?BaseResult.fail():BaseResult.success(weather);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }
}
