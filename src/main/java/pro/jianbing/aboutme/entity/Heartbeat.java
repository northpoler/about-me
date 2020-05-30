package pro.jianbing.aboutme.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 李建兵
 */
@Data
public class Heartbeat implements Serializable {
    private static final long serialVersionUID = -2171972767495679875L;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 页面
     */
    private String pageName;
    /**
     * 发送时间
     */
    private String sendTime;
}
