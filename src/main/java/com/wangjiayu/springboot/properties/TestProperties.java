package com.wangjiayu.springboot.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test", ignoreUnknownFields = true)
@Component
@Data
public class TestProperties {

    /**
     * msg
     */
    private String msg = "msg";
    private String msg1 = "msg1";
    private String msg2 = "msg2";

}
