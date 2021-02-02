package com.gla.demo.config;

import com.gla.demo.core.UserGateway;
import com.gla.demo.core.service.UserService;
import com.gla.demo.core.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraBeansConfiguration {

    @Bean
    UserService userService(UserGateway userGateway) {
        return new UserServiceImpl(userGateway);
    }
}
