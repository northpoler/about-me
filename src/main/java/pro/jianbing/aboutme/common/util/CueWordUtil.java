package pro.jianbing.aboutme.common.util;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * 提示词工具（搜索用，数据来自百度）
 *
 * @author 李建兵
 */
public final class CueWordUtil {
    private static final String URL = "https://www.baidu.com/su?cb=result&wd=";
    public static List<String> getCueWords(String keyword){
        try {
            keyword = keyword.replaceAll(" ","+");
            String result = HttpUtil.doGet(URL+keyword);
            result = result.replace("result(", "");
            result = result.substring(0,result.lastIndexOf(")"));
            Map map = JSON.parseObject(result, Map.class);
            return  (List<String>)map.get("s");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
