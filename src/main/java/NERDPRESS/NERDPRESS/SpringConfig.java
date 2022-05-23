package NERDPRESS.NERDPRESS;

import NERDPRESS.NERDPRESS.Repository.*;
import NERDPRESS.NERDPRESS.interceptor.LoginInterceptor;
import NERDPRESS.NERDPRESS.service.NovelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public NovelService NovelService() {
        return new NovelService(memberRepositoryInterface(), novelRepositoryInterface());
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInterface() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public NovelRepositoryInterface novelRepositoryInterface() {
        return new JdbcTemplateNovelRepository(dataSource);
    }

    @Bean
    public QuestRepositoryInterface questRepositoryInterface() {return new JdbcTemplateQuestRepository(dataSource);}

    @Bean
    public LicenseRepositoryInterface licenseRepositoryInterface() {return new JdbcTemplateLicenseRepository(dataSource);}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/mypage","/nextepisode");

    }
}
