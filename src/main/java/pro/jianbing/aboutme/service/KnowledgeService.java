package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.repository.KnowledgeRepositoty;
import pro.jianbing.aboutme.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@Service
public class KnowledgeService {

    private final
    KnowledgeRepositoty knowledgeRepositoty;

    @Autowired
    public KnowledgeService(KnowledgeRepositoty knowledgeRepositoty) {
        this.knowledgeRepositoty = knowledgeRepositoty;
    }

    @Transactional
    public Integer save(Knowledge knowledge, HttpServletRequest request){
        try {
            String ipAddress = NetworkUtil.getIpAddress(request);
            knowledge.setIp(ipAddress);
            knowledge.setEditTime(LocalDateTime.now());
            Knowledge save = knowledgeRepositoty.save(knowledge);
            if (save!=null){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public String get(){
        try {
            String knowledge = knowledgeRepositoty.getOne();
            return knowledge;
        } catch (Exception e) {
            e.printStackTrace();
            return "内容出错";
        }
    }
}
