package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.entity.Logo;
import pro.jianbing.aboutme.repository.LogoRepository;

import javax.transaction.Transactional;

/**
 * @author Jianbing
 */
@Service
public class LogoService {

    private final
    LogoRepository logoRepository;

    @Autowired
    public LogoService(LogoRepository logoRepository) {
        this.logoRepository = logoRepository;
    }

    public String getLogoByUserId(Long userId){
        Logo logo = logoRepository.findByUserIdAndMark(userId, GlobalString.MARK_NORMAL);
        if (null==logo || null==logo.getSrc()){
            return "../static/image/logo_cat.png";
        }
        return logo.getSrc();
    }

    public Logo getTempLogoByUserId(Long userId){
        return logoRepository.findByUserIdAndMark(userId,"2");
    }

    @Transactional
    public Logo save(Logo logo){
        return logoRepository.save(logo);
    }
}
