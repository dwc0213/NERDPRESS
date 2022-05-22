package NERDPRESS.NERDPRESS;

import NERDPRESS.NERDPRESS.Repository.JdbcMemberRepository;
import NERDPRESS.NERDPRESS.Repository.JdbcTemplateNovelRepository;
import NERDPRESS.NERDPRESS.Repository.NovelRepositoryInterface;
import NERDPRESS.NERDPRESS.interceptor.LoginInterceptor;
import NERDPRESS.NERDPRESS.service.NovelService;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;
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
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public NovelRepositoryInterface novelRepositoryInterface() {
        return new JdbcTemplateNovelRepository(dataSource);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/mypage","/nextepisode");

    }
}
