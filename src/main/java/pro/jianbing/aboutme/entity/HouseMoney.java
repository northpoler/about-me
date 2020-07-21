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
    @Column(name = "plan_money")
    private Double planMoney;
    /**
     * 实际到账(万)
     */
    @Column(name = "real_money")
    private Double realMoney;
    /**
     * 最终到款时间
     */
    @Column(name = "received_time")
    private LocalDateTime receivedTime;
    /**
     * 是否需偿还 0否1是
     */
    @Column(name = "need_repay")
    private String needRepay;
    /**
     * 偿还(万)
     */
    private Double repay;
    /**
     * 最终还款时间
     */
    @Column(name = "repay_time")
    private LocalDateTime repayTime;
    /**
     * 备注
     */
    private String note;
    /**
     * 状态 0未知晓 1同意 2待定 3拒绝
     */
    private String state;
    /**
     * 顺序
     */
    private Integer sequence;
    /**
     * 标记 0正常1删除
     */
    private String mark;
}
