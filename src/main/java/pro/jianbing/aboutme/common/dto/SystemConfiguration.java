package pro.jianbing.aboutme.common.dto;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统配置
 *
 * @author jianbing
 */
@Data
@Entity
@Table(name = "system_configuration")
public class SystemConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String item;
    private String title;
    private String value;
    /**
     * 类型 0：普通输入框 1：带单位的输入框 2：单选框 3：时间
     */
    private String type;
    /**
     * 备注 可以用作页面上的提示
     */
    private String remark;
    /**
     * 扩展值，输入框的单位，单选的选项
     */
    private String extension;
    private Integer sequence;
    private String mark;
}
