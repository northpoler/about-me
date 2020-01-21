package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Link;
import pro.jianbing.aboutme.repository.LinkRepositoty;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class LinkService {

    private final
    LinkRepositoty linkRepositoty;

    @Autowired
    public LinkService(LinkRepositoty linkRepositoty) {
        this.linkRepositoty = linkRepositoty;
    }

    public List<Link> getLinkList(){
        List<Link> allByMark = linkRepositoty.findAllByMarkLessThanEqualOrderBySequenceAsc(GlobalString.MARK_NORMAL);
        return allByMark;
    }
}
