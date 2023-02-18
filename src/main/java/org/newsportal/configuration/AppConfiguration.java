package org.newsportal.configuration;

import org.hibernate.SessionFactory;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.repository.impl.UserRepositoryImpl;
import org.newsportal.database.util.HibernateUtil;
import org.newsportal.service.UserService;
import org.newsportal.service.impl.UserServiceImpl;
import org.newsportal.service.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("org.newsportal")
@EnableWebMvc
public class AppConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    @Bean
    public UserService userService(UserRepository userRepository, UserMapper mapper) {
        return new UserServiceImpl(userRepository, mapper);
    }

    @Bean
    public UserRepository userRepository(SessionFactory sessionFactory) {
        return new UserRepositoryImpl(sessionFactory);
    }
}
