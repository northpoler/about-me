package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author DefaultAccount
 */
@Data
public class Like implements Serializable {
    private static final long serialVersionUID = -6683776601547996277L;
    private String id;
    private Timestamp likeTime;
    @Column(name = "user_id")
    private Long userId;
    private String ip;
}
