package org.zhh.activitydaemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("org.zhh.activitydaemon.dao")
public class ActivityDaemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityDaemonApplication.class, args);
    }

}
