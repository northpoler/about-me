package pro.jianbing.aboutme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.service.KeywordService;
import pro.jianbing.aboutme.service.LikeService;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

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
    public Integer saveKeyword(@RequestParam("keyword") String word, HttpServletRequest request){
        Keyword keyword = new Keyword();
        keyword.setKeyword(word);
        keyword.setIp(NetworkUtil.getIpAddress(request));
        keyword.setSearchTime(LocalDateTime.now());
        keyword.setMark("0");
        Integer result = keywordService.saveKeyword(keyword);
        return result;
    }
}
