package com.bizz.hello.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Slf4j
@Component
public class BeanLogger {

    @Bean
    public CommandLineRunner logBeans(ApplicationContext ctx) {
        return args -> {
            log.info("========== Registered Beans ==========");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            log.info("======================================");
        };
    }
}
