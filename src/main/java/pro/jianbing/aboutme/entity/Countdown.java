package pro.jianbing.aboutme.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@Data
public class Countdown implements Serializable {
    private static final long serialVersionUID = -1811772377015660792L;
    private String id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endTime;
    private Integer days;
}
