package pro.jianbing.aboutme.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.dto.SystemConfiguration;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.repository.system.SystemConfigurationRepository;

import java.util.List;

@Service
public class SystemConfigurationService {

    private final
    SystemConfigurationRepository Repository;

    @Autowired
    public SystemConfigurationService(SystemConfigurationRepository Repository) {
        this.Repository = Repository;
    }

    public List<SystemConfiguration> getAll() {
        return Repository.getAllByMark(GlobalString.MARK_NORMAL);
    }


}
