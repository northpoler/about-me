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
@Table(name="link")
public class Link implements Serializable {
    private static final long serialVersionUID = 2869135947073336189L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 链接标题
     */
    private String title;
    /**
     * 链接
     */
    private String link;
    /**
     * 图标 layui
     */
    private String icon;
    /**
     * 显示顺序
     */
    private Integer sequence;
    /**
     * 标记 0正常 1删除 2隐藏
     */
    private String mark;
}
