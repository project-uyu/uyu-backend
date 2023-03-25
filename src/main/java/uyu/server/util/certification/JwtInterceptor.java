package uyu.server.util.certification;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import uyu.server.member.data.entity.Member;
import uyu.server.member.service.MemberService;

@Slf4j
@Component
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            String token = request.getHeader(AUTHORIZATION_HEADER);

            if (token == null || !token.startsWith(TOKEN_PREFIX)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            token = token.substring(TOKEN_PREFIX.length());

            try {
                Claims claims = jwtUtil.validateToken(token);
                log.info("validateToken");
                Member member =  memberService.findMemberByEmail((String) claims.get("email"));
                log.info("로그인 시도 email : " + (String) claims.get("email"));

            } catch (Exception ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
        }


        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            if (ex instanceof SignatureException) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else if (ex instanceof ExpiredJwtException) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }
    }
}


