package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.repository.CountdownRepositoty;

import java.time.LocalDateTime;
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

    public Countdown getCountdown(Countdown countdown) {
        countdown.setEndTime(LocalDateTime.now());
        List<Countdown> countdownList = countdownRepositoty.getByUserId(countdown.getUserId(),countdown.getEndTime());
        if (null==countdownList||countdownList.size()==0){
            countdown = new Countdown();
            countdown.setTitle("元旦");
            countdown.setEndTime(LocalDateTime.of(
                    LocalDateTime.now().plusYears(1).getYear(),
                    1,1,0,0,0,0));
        } else {
            countdown = countdownList.get(0);
        }
        return countdown;
    }

    @Transactional
    public Integer save(Countdown countdown) {
        Countdown save = countdownRepositoty.save(countdown);
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
