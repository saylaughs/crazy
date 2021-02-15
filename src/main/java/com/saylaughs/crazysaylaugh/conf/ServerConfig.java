package com.saylaughs.crazysaylaugh.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server.servlet")
@Data
public class ServerConfig {

    private String contextPath;

    private Integer port;
}
