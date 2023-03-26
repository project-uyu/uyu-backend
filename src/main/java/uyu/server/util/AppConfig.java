package uyu.server.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uyu.server.util.certification.JwtInterceptor;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/users/admin","/api/users/admin/member/{memberId}"); // JWT 인증이 필요한 API 경로를 지정합니다.
//                .excludePathPatterns(); // JWT 인증이 필요하지 않은 API 경로를 제외합니다.
    }
}
