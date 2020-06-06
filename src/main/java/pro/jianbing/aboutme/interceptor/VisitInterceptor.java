package pro.jianbing.aboutme.interceptor;

import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pro.jianbing.aboutme.common.enums.UrlEnum;
import pro.jianbing.aboutme.common.global.GlobalObject;
import pro.jianbing.aboutme.common.global.GlobalString;
import pro.jianbing.aboutme.common.util.EncryptionUtil;
import pro.jianbing.aboutme.common.util.NetworkUtil;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.entity.Visit;
import pro.jianbing.aboutme.service.UserService;
import pro.jianbing.aboutme.service.VisitService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author 李建兵
 */
@Component
public class VisitInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(VisitInterceptor.class);
    /**
     * 管理端页面url开头
     */
    private static final String MANAGE_URL = "/manage";
    private final VisitService visitService;
    private final UserService userService;

    @Autowired
    public VisitInterceptor(UserService userService, VisitService visitService) {
        this.userService = userService;
        this.visitService = visitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer url = request.getRequestURL();
        String subUrl = url.substring(7);
        int lastIndex = subUrl.indexOf("/");
        subUrl = subUrl.substring(lastIndex);
        HttpSession session = request.getSession();
        if (subUrl.contains(";jsessionid")){
            subUrl = subUrl.substring(0,subUrl.indexOf(";jsessionid"));
        }
        //如果访问特定页面，记录访问信息
        if (UrlEnum.hasValue(subUrl)){
            Visit visit = new Visit();
            String ipAddress = NetworkUtil.getIpAddress(request);
            /*String addressByIp = NetworkUtil.getAddressByIp(ipAddress);*/
            visit.setIp(ipAddress);
            /*visit.setAddress(addressByIp);*/
            visit.setTarget(subUrl);
            User user = (User) request.getSession().getAttribute(GlobalString.ATTRIBUTE_USER);
            if (null!=user){
                visit.setUserId(user.getId());
            }
            visit.setVisitTime(LocalDateTime.now());
            visitService.saveVisit(visit);
            UrlEnum urlEnum = UrlEnum.getEnumByValue(subUrl);
            if (null != urlEnum) {
                GlobalObject.VISIT_COUNT.put(urlEnum.getCode(),GlobalObject.VISIT_COUNT.get(urlEnum.getCode())+1);
            }
        }
        if (session.getAttribute(GlobalString.ATTRIBUTE_USER) == null){
            String loginToken = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length >0){
                for (Cookie cookie : cookies){
                    if (GlobalString.COOKIE_REMEMBER.equals(cookie.getName())){
                        loginToken = cookie.getValue();
                    }
                }
            }
            if (!"".equals(loginToken.trim())){
                loginToken = EncryptionUtil.decrypt(loginToken);
                String[] strs = loginToken.split(":");
                if (strs.length==3){
                    User user = userService.FindUserByUsername(strs[0]);
                    if (null!=user && strs[1].equals(user.getPassword())){
                        request.getSession().setAttribute(GlobalString.ATTRIBUTE_USER,user);
                    }
                }
            }
        }
        if (subUrl.indexOf(MANAGE_URL)==0) {
            User user = (User) session.getAttribute(GlobalString.ATTRIBUTE_USER);
            if (null == user || !GlobalString.ROLE_ADMIN.equals(user.getRole())){
                response.sendRedirect(request.getContextPath()+"/unauthorized");
            }
        }
        return true;
    }
}
