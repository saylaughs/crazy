package com.saylaughs.crazysaylaugh;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@SpringBootApplication
@EnableCaching
@MapperScan(value = "com.saylaughs.crazysaylaugh.Dao")
@Slf4j
public class CrazySaylaughApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CrazySaylaughApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));

        SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
    }
}
