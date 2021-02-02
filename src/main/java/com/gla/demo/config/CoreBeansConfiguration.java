package com.gla.demo.config;

import com.gla.demo.core.UserGateway;
import com.gla.demo.infra.sql.UserSqlGateway;
import com.gla.demo.infra.sql.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class CoreBeansConfiguration {

    @Bean
    UserGateway userGateway(UserRepository userRepository) {
        return new UserSqlGateway(userRepository);
    }
}
