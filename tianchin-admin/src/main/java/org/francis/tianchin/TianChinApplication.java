package org.francis.tianchin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author tianchin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TianChinApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(TianChinApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  TianChin健身启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
