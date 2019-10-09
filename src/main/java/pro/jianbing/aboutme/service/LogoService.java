package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Logo;
import pro.jianbing.aboutme.repository.LogoRepositoty;

/**
 * @author Jianbing
 */
@Service
public class LogoService {

    private final
    LogoRepositoty logoRepositoty;

    @Autowired
    public LogoService(LogoRepositoty logoRepositoty) {
        this.logoRepositoty = logoRepositoty;
    }

    public String getLogoByUserId(Long userId){
        Logo logo = logoRepositoty.findByUserId(userId);
        if (null==logo || null==logo.getSrc()){
            return "../static/image/logo.png";
        }
        return logo.getSrc();
    }
}
