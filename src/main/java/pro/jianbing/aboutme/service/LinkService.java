package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Keyword;
import pro.jianbing.aboutme.entity.Link;
import pro.jianbing.aboutme.repository.KeywordRepositoty;
import pro.jianbing.aboutme.repository.LinkRepositoty;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class LinkService {

    private static final String DOMAIN_COMPANY = "company";
    private final
    LinkRepositoty linkRepositoty;

    @Autowired
    public LinkService(LinkRepositoty linkRepositoty) {
        this.linkRepositoty = linkRepositoty;
    }

    public List<Link> getLinkList(String domain){
        String mark = "0";
        if (DOMAIN_COMPANY.equals(domain)){
            mark = "1";
        }
        List<Link> allByMark = linkRepositoty.findAllByMarkLessThanEqualOrderBySequenceAsc(mark);
        return allByMark;
    }
}
