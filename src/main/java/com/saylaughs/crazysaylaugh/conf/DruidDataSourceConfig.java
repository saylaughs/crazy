package com.saylaughs.crazysaylaugh.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidDataSourceConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean
    public DruidDataSource druid(){
       return new DruidDataSource();
    }



    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> map=new HashMap<String, String>();
        map.put("loginUsername","hwl");
        map.put("loginPassword","1");
        map.put("allow","");
        map.put("deny","");

        bean.setInitParameters(map);
        return bean;
    }


    @Bean
    public FilterRegistrationBean webStatFilter()
    {
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> map=new HashMap<String, String>();
        bean.setInitParameters(map);
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setUrlPatterns(Arrays.asList("/druid/*"));
        return bean;
    }


}
