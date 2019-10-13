package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author jianbing
 */
@Data
@Entity
@Table(name="logo")
public class Logo implements Serializable {
    private static final long serialVersionUID = -6683776601547996277L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String src;
    @Column(name = "save_time")
    private LocalDateTime saveTime;
    private String mark;

    public Logo() {
    }

    public Logo(Long userId, String src, LocalDateTime saveTime, String mark) {
        this.userId = userId;
        this.src = src;
        this.saveTime = saveTime;
        this.mark = mark;
    }
}
