package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.repository.CompanionRepositoty;

@Service
public class CompanionService {

    private final
    CompanionRepositoty companionRepositoty;

    @Autowired
    public CompanionService(CompanionRepositoty companionRepositoty) {
        this.companionRepositoty = companionRepositoty;
    }

    public int countMembers() {
        return companionRepositoty.countCompanions();
    }
}
