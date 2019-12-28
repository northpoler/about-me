package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.jianbing.aboutme.entity.Countdown;
import pro.jianbing.aboutme.pojo.CountdownDto;
import pro.jianbing.aboutme.repository.CountdownRepositoty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CountdownService {

    private final
    CountdownRepositoty countdownRepositoty;

    @Autowired
    public CountdownService(CountdownRepositoty countdownRepositoty) {
        this.countdownRepositoty = countdownRepositoty;
    }

    public CountdownDto getCountdown(Countdown countdown) {
        countdown.setEndTime(LocalDateTime.now());
        List<Countdown> countdownList = countdownRepositoty.getByUserId(countdown.getUserId(),countdown.getEndTime());
        CountdownDto countdownDto = new CountdownDto();
        if (null==countdownList||countdownList.size()==0){
            countdownDto.setTitle("元旦");
            countdownDto.setEndTime(LocalDateTime.of(
                    LocalDateTime.now().plusYears(1).getYear(),
                    1,1,0,0,0,0));
            countdownDto.setDate(LocalDate.now().plusYears(1L).format(DateTimeFormatter.ofPattern("yyyy"))+"-01-01");
            countdownDto.setTime("00:00:00");
        } else {
            countdown = countdownList.get(0);
            countdownDto.setId(countdown.getId());
            countdownDto.setTitle(countdown.getTitle());
            countdownDto.setEndTime(countdown.getEndTime());
            countdownDto.setDate(countdown.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            countdownDto.setTime(countdown.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
        return countdownDto;
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
