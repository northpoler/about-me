package pro.jianbing.aboutme.service;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Knowledge;
import pro.jianbing.aboutme.repository.KnowledgeRepository;

/**
 * @author DefaultAccount
 */
@Service
public class KnowledgeService {

    private final
    KnowledgeRepository knowledgeRepository;

    @Autowired
    public KnowledgeService(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }

    @Transactional
    public Integer save(Knowledge knowledge){
        try {
            Knowledge save = knowledgeRepository.save(knowledge);
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
            String knowledge = knowledgeRepository.getByUserId(userId);
            if (StringUtil.isNullOrEmpty(knowledge)){
                knowledge = "<p>在这里，可以随时记录<b>任何你想记录的信息</b> .......</p>";
            }
            return knowledge;
        } catch (Exception e) {
            e.printStackTrace();
            return "内容出错";
        }
    }
}
