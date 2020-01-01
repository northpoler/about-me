package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.common.dto.BaseResult;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.service.KeywordService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final
    KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping("insert")
    public BaseResult saveKeyword(@RequestParam("keyword") String word, HttpServletRequest request){
        BaseResult baseResult;
        try {
            Keyword keyword = new Keyword();
            keyword.setKeyword(word);
            keyword.setIp(NetworkUtil.getIpAddress(request));
            keyword.setSearchTime(LocalDateTime.now());
            keyword.setMark("0");
            User user = (User)request.getSession().getAttribute("user");
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
}
