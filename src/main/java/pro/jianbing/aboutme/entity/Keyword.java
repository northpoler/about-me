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
@Table(name="keyword")
public class Keyword implements Serializable {
    private static final long serialVersionUID = -684212631938319120L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    private String keyword;
    @Column(name = "search_time")
    private LocalDateTime searchTime;
    private String ip;
    private String mark;
}
