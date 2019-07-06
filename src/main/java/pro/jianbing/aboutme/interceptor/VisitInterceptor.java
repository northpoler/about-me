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
import java.time.LocalDateTime;

/**
 * @author 李建兵
 */
@Component
public class VisitInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(VisitInterceptor.class);
    private static final String INDEX_URL = "/";
    private static final String SEARCH_URL = "/search";
    @Autowired
    private VisitService visitService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer url = request.getRequestURL();
        log.info("拦截请求："+url);
        int lastIndex = url.lastIndexOf("/");
        String urlSubString = url.substring(lastIndex);
        log.info("具体的请求是："+urlSubString);
        //如果访问首页或是搜索页，记录访问信息
        if (INDEX_URL.equals(urlSubString)||SEARCH_URL.equals(urlSubString)){
            String ipAddress = NetworkUtil.getIpAddress(request);
            String addressByIp = NetworkUtil.getAddressByIp(ipAddress);
            Visit visit = new Visit();
            visit.setIp(ipAddress);
            visit.setAddress(addressByIp);
            visit.setVisitTime(LocalDateTime.now());
            visitService.saveVisit(visit);
        }
        return true;
    }
}
