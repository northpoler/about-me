package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Logo;
import pro.jianbing.aboutme.repository.LogoRepositoty;

import javax.transaction.Transactional;

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
        Logo logo = logoRepositoty.findByUserIdAndMark(userId,"0");
        if (null==logo || null==logo.getSrc()){
            return "../static/image/logo.png";
        }
        return logo.getSrc();
    }

    public Logo getTempLogoByUserId(Long userId){
        return logoRepositoty.findByUserIdAndMark(userId,"2");
    }

    @Transactional
    public Logo save(Logo logo){
        return logoRepositoty.save(logo);
    }
}
