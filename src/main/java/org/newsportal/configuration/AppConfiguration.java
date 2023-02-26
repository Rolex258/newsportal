package org.newsportal.configuration;

import org.hibernate.SessionFactory;
import org.newsportal.database.util.HibernateUtil;
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
//
//    @Bean
//    public UserService userService(UserRepository userRepository, UserMapper mapper) {
//        return new UserServiceImpl(userRepository, mapper);
//    }
//
//    @Bean
//    public UserRepository userRepository(SessionFactory sessionFactory) {
//        return new UserRepositoryImpl(sessionFactory);
//    }

//    @Bean
//    public ArticleService articleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
//        return new ArticleServiceImpl(articleRepository, articleMapper);
//    }
}
