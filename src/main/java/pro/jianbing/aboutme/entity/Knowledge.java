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
@Table(name="knowledge")
public class Knowledge implements Serializable {
    private static final long serialVersionUID = -8704236171357522020L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String title;
    private String content;
    @Column(name = "edit_time")
    private LocalDateTime editTime;
    private String ip;
    private String mark;
}
