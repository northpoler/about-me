package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.CueWordUtil;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.KeywordService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DefaultAccount
 */
@RestController
@RequestMapping("/keyword")
public class KeywordController extends BaseController {

    private final
    KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping("insert")
    public BaseResult saveKeyword(@RequestParam("keyword") String word){
        BaseResult baseResult;
        try {
            Keyword keyword = new Keyword();
            keyword.setKeyword(word);
            keyword.setIp(getIpByRequest());
            keyword.setSearchTime(LocalDateTime.now());
            keyword.setMark("0");
            User user = getUser();
            if (null!=user){
                keyword.setUserId(user.getId());
            }
            Integer result = keywordService.saveKeyword(keyword);
            baseResult = result>0?BaseResult.success():BaseResult.fail();
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResult.systemError();
        }
        return baseResult;
    }

    @GetMapping("tips")
    public Map<String,Object> getCueWords(String keywords){
        Map<String,Object> result = new HashMap<>();
        try {
            List<String> cueWords = CueWordUtil.getCueWords(keywords);
            result.put("code",0);
            result.put("type","success");
            result.put("content",cueWords);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",500);
        }
        return result;
    }
}
