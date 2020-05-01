package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author DefaultAccount
 */
@Data
@Entity
@Table(name = "companion")
public class Companion implements Serializable {
    private static final long serialVersionUID = 1591362168833597928L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 顺序
     */
    private Integer sequence;
    /**
     * 标记 0正常 1删除 2隐藏
     */
    private String mark;
    /**
     * 备注
     */
    private String note;
}
