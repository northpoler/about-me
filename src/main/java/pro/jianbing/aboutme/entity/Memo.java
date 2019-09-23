package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author DefaultAccount
 */
@Data
@Entity
@Table(name="memo")
public class Memo implements Serializable {
    private static final long serialVersionUID = -531202573737283805L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String title;
    private String address;
    private String username;
    private String password;
    private String remark;
    private Long sequence;
    private String mark;
}
