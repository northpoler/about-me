package pro.jianbing.aboutme.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 李建兵
 */
@Data
public class CountdownDto {
    private Long id;
    private String field;
    private String value;
    private String title;
    private String date;
    private String time;
    private String days;
    private LocalDateTime endTime;
}
