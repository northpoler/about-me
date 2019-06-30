package pro.jianbing.aboutme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.entity.Keyword;


/**
 * @author DefaultAccount
 */
@Repository("keywordRepository")
public interface KeywordRepositoty extends JpaRepository<Keyword,Long> {
}
