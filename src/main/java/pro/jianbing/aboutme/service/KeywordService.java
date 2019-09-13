package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.repository.KeywordRepositoty;

/**
 * @author DefaultAccount
 */
@Service
public class KeywordService {

    private final
    KeywordRepositoty keywordRepositoty;

    @Autowired
    public KeywordService(KeywordRepositoty keywordRepositoty) {
        this.keywordRepositoty = keywordRepositoty;
    }

    @Transactional
    public Integer saveKeyword(Keyword keyword){
        Keyword save = keywordRepositoty.save(keyword);
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
