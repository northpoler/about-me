package pro.jianbing.aboutme.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.dto.SystemConfiguration;
import pro.jianbing.aboutme.repository.system.SystemConfigurationRepositoty;

import java.util.List;

@Service
public class SystemConfigurationService {

    private final
    SystemConfigurationRepositoty repositoty;

    @Autowired
    public SystemConfigurationService(SystemConfigurationRepositoty repositoty) {
        this.repositoty = repositoty;
    }

    public List<SystemConfiguration> getAll() {
        return repositoty.getAll();
    }


}
