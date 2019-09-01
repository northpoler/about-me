package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.repository.CountdownRepositoty;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountdownService {

    private final
    CountdownRepositoty countdownRepositoty;

    @Autowired
    public CountdownService(CountdownRepositoty countdownRepositoty) {
        this.countdownRepositoty = countdownRepositoty;
    }

    public List<Countdown> getTwoCountdown(Countdown countdown) {
        List<Countdown> twoCountdown = new ArrayList<>(2);
        if (null == countdown || null == countdown.getUserId()){
            twoCountdown = countdownRepositoty.getTwoWithoutUser();
        }
        return twoCountdown;
    }
}
