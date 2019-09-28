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
@Table(name="likes")
public class Like implements Serializable {
    private static final long serialVersionUID = -6683776601547996277L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "like_time")
    private LocalDateTime likeTime;
    @Column(name = "user_id")
    private Long userId;
    private String ip;
}
