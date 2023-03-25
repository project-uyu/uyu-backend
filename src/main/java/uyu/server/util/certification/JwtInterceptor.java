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
    private static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";
    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";
    private static final String REFRESH_TOKEN_PREFIX = "Refresh ";

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler){

        if (handler instanceof HandlerMethod) {
            String accessToken = request.getHeader(AUTHORIZATION_HEADER);
            String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);

            if (accessToken == null || !accessToken.startsWith(AUTHORIZATION_TOKEN_PREFIX)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }

            accessToken = accessToken.substring(AUTHORIZATION_TOKEN_PREFIX.length());

            try {
                Claims claims = jwtUtil.validateToken(accessToken);
                log.info("Access token validation success");
                Member member =  memberService.findMemberByEmail((String) claims.get("email"));
                log.info("Trying to log in email : " + (String) claims.get("email"));

            } catch (Exception ex) {

                if(refreshToken == null || !refreshToken.startsWith(REFRESH_TOKEN_PREFIX)) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }
                refreshToken = refreshToken.substring(REFRESH_TOKEN_PREFIX.length());

                try {
                    Claims claims = jwtUtil.validateRefreshToken(refreshToken);
                    log.info("Refresh token validation success");
                    Member member =  memberService.findMemberByEmail((String) claims.get("email"));
                    log.info("Trying to log in email : " + (String) claims.get("email"));
                    String newAccessToken = jwtUtil.createAccessToken(member);
                    response.setHeader(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN_PREFIX + newAccessToken);

                }catch (Exception e) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }
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


