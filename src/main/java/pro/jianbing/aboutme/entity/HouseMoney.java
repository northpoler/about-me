package pro.jianbing.aboutme.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author DefaultAccount
 */
@Data
@Entity
@Table(name="house_money")
public class HouseMoney implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 出资人
     */
    private String person;
    /**
     * 计划筹资(万)
     */
    private Double plan;
    /**
     * 实际到账(万)
     */
    private Double real;
    /**
     * 备注
     */
    private String note;
    /**
     * 顺序
     */
    private Integer sequence;
    private String mark;
}
