package com.saylaughs.crazysaylaugh.conf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;
import sun.security.action.GetLongAction;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converter.setObjectMapper(mapper);
        return converter;
    }

    //添加转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //将我们定义的时间格式转换器添加到转换器列表中,
    //这样jackson格式化时候但凡遇到Date类型就会转换成我们定义的格式
        converters.add(jackson2HttpMessageConverter());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    @Override
    public HandlerMapping viewControllerHandlerMapping(PathMatcher pathMatcher, UrlPathHelper urlPathHelper, FormattingConversionService conversionService, ResourceUrlProvider resourceUrlProvider) {
        return super.viewControllerHandlerMapping(pathMatcher, urlPathHelper, conversionService, resourceUrlProvider);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Override
    public HandlerMapping resourceHandlerMapping(UrlPathHelper urlPathHelper, PathMatcher pathMatcher, ContentNegotiationManager contentNegotiationManager, FormattingConversionService conversionService, ResourceUrlProvider resourceUrlProvider) {


        return super.resourceHandlerMapping(urlPathHelper, pathMatcher, contentNegotiationManager, conversionService, resourceUrlProvider);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("访问静态资源");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
