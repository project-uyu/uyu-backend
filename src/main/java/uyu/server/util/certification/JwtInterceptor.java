package uyu.server.util.certification;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
            if (permission != null) {
                String token = request.getHeader(AUTHORIZATION_HEADER);

                if(token == null || !token.startsWith(TOKEN_PREFIX)){
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }

                token = token.substring(TOKEN_PREFIX.length());

                try {
                    Claims claims =Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
                    String userRole = (String) claims.get("role");

                    if(userRole == null || !userRole.equals(permission.role().toString())){
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return false;
                    }
                }catch (JwtException ex){
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


