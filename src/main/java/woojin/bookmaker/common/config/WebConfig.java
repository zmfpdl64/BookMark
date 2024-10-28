package woojin.bookmaker.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해
                .allowedOrigins("http://localhost:3000") // 클라이언트의 도메인
                .allowedMethods("GET", "POST") // 허용할 HTTP 메소드
                .allowCredentials(true); // 쿠키, 인증 헤더 포함 여부
    }
}