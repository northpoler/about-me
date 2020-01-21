package pro.jianbing.aboutme.repository.system;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.jianbing.aboutme.common.dto.SystemConfiguration;

import java.util.List;


/**
 * @author 李建兵
 */
@Repository("systemConfigurationRepositoty")
public interface SystemConfigurationRepositoty extends JpaRepository<SystemConfiguration,Long> {
    List<SystemConfiguration> getAllByMark(String mark);
}
