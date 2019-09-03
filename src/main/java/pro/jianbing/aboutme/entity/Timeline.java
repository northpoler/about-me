package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DefaultAccount
 */
@Data
@Entity
@Table(name="timeline")
public class Timeline implements Serializable {
    private static final long serialVersionUID = 4940934337505833314L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "occur_time")
    private String occurTime;
    private String content;
    private String contributor;
    @Column(name = "insert_time")
    private LocalDateTime insertTime;
    private String ip;
    private Integer sequence;
    private String mark;
}
