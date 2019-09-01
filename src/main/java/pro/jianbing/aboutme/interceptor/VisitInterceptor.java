package pro.jianbing.aboutme.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pro.jianbing.aboutme.entity.Visit;
import pro.jianbing.aboutme.service.VisitService;
import pro.jianbing.aboutme.util.NetworkUtil;

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
    private static final String INDEX_URL = "/";
    private static final String SEARCH_URL = "/search";
    private static final String COMPANY_IP = "122.224.218.34";
    private static final String COMPANY_ADDRESS = "本地局域网";
    @Autowired
    private VisitService visitService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer url = request.getRequestURL();
        log.info("拦截请求："+url);
        int lastIndex = url.lastIndexOf("/");
        String urlSubString = url.substring(lastIndex);
        log.info("具体的请求是："+urlSubString);
        //获取IP和登陆地，以便进行权限控制
        HttpSession session = request.getSession();
        Object domain = session.getAttribute("domain");
        if (null==domain){
            String ipAddress = NetworkUtil.getIpAddress(request);
            String addressByIp = NetworkUtil.getAddressByIp(ipAddress);
            if (COMPANY_IP.equals(ipAddress)||COMPANY_ADDRESS.equals(addressByIp)){
                session.setAttribute("domain","company");
            } else {
                session.setAttribute("domain","society");
            }
        }
        //如果访问首页或是搜索页，记录访问信息
        if (INDEX_URL.equals(urlSubString)||SEARCH_URL.equals(urlSubString)){
            Visit visit = new Visit();
            String ipAddress = NetworkUtil.getIpAddress(request);
            String addressByIp = NetworkUtil.getAddressByIp(ipAddress);
            visit.setIp(ipAddress);
            visit.setAddress(addressByIp);
            visit.setVisitTime(LocalDateTime.now());
            visitService.saveVisit(visit);
        }
        return true;
    }
}