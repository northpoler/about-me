package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Knowledge;


/**
 * @author DefaultAccount
 */
@Repository("knowledgeRepository")
public interface KnowledgeRepositoty extends JpaRepository<Knowledge,Long> {
    /**
     * 获取最新一条内容
     * @param
     * @return
     */
    @Query("select content from Knowledge where id = (select max(id) from Knowledge)")
    String getOne();
}
