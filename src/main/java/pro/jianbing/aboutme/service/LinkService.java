package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Link;
import pro.jianbing.aboutme.repository.LinkRepository;

import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class LinkService {

    private final
    LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> getLinkList(){
        List<Link> allByMark = linkRepository.findAllByMarkLessThanEqualOrderBySequenceAsc(GlobalString.MARK_NORMAL);
        return allByMark;
    }
}
