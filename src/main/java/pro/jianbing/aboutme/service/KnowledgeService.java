package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.repository.KnowledgeRepositoty;

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
            User user = (User)request.getSession().getAttribute("user");
            knowledge.setUserId(user.getId());
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

    public String getByUserId(Long userId){
        try {
            return knowledgeRepositoty.getByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return "内容出错";
        }
    }
}
