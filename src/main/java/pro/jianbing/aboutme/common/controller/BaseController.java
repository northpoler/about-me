package pro.jianbing.aboutme.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: about-me
 * @BelongsPackage: pro.jianbing.aboutme.common.controller
 * @Author: jianbing
 * @CreateTime: 2020-01-20 16:13
 * @Description: ${Description}
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取当前登录的用户
     * @return
     */
    public User getUser() {
        User user = null;
        try {
            user = (User) request.getSession().getAttribute(GlobalString.ATTRIBUTE_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过请求获取IP
     * @return
     */
    public String getIpByRequest() {
        return NetworkUtil.getIpAddress(request);
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
