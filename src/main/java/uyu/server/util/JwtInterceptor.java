package uyu.server.util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //클라이언트 요청에서 JWT 토큰 추출
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String token = authorizationHeader.substring(7);

        // JWT 토큰 검증
        try {
            Claims claims = Jwts.parser().setSigningKey("secret".getBytes()).parseClaimsJws(token).getBody();
            request.setAttribute("userId", claims.get("userId"));
        } catch (JwtException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
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


