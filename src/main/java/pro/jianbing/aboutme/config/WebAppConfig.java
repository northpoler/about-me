package pro.jianbing.aboutme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pro.jianbing.aboutme.interceptor.VisitInterceptor;

/**
 * @author 李建兵
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    @Autowired
    private VisitInterceptor visitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(visitInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/static/*");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
