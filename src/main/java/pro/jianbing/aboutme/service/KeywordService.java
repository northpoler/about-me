package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.repository.KeywordRepository;

/**
 * @author DefaultAccount
 */
@Service
public class KeywordService {

    private final
    KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Transactional
    public Integer saveKeyword(Keyword keyword){
        Keyword save = keywordRepository.save(keyword);
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
