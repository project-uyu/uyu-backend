package uyu.server.util.certification;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import uyu.server.member.data.entity.Member;

import java.sql.Date;

@Component
@AllArgsConstructor
@Slf4j
public class JwtUtil {

    private final String SECRET_KEY = "secretKey";
    private final Long ACCESS_TOKEN_EXP = 1000L * 60 * 60;

    private final Long REFRESH_TOKEN_EXP = 1000L * 60 * 60 * 24 * 7;

    private final StringRedisTemplate redisTemplate;

    public String getSecretKey() {
        return SECRET_KEY;
    }


    //엑세스 토큰 생성
    public String createAccessToken(Member member) { // 토큰에 담고싶은 값 파라미터로 가져오기
        return Jwts.builder()
                .setHeaderParam("typ", "JWT") // 토큰 타입
                .setSubject("userToken") // 토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP)) // 토큰 유효시간
                .claim("email", member.getEmail()) //토큰에 담을 데이터
                .claim("memberId", member.getId())
                .signWith(SignatureAlgorithm.HS256, getSecretKey().getBytes()) // secretKey를 사용하여 해싱 암호화 알고리즘 처리
                .compact(); // 직렬화, 문자열로 변경
    }

    //리프레시 토큰 검사
    public String createRefreshToken(Member member) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("refreshToken")
                .setExpiration(new Date(System.currentTimeMillis() + (REFRESH_TOKEN_EXP * 100)))
                .claim("email", member.getEmail()) //토큰에 담을 데이터
                .claim("memberId", member.getId())
                .signWith(SignatureAlgorithm.HS256, getSecretKey().getBytes())
                .compact();
    }


    // 토큰 유효성 검사
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey().getBytes())
                .requireSubject("userToken")
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims validateRefreshToken(String refreshToken) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .requireSubject("refreshToken")
                    .parseClaimsJws(refreshToken)
                    .getBody();
            String email = (String) claims.get("email");
            String storedRefreshToken = redisTemplate.opsForValue().get(email);
            if (!refreshToken.equals(storedRefreshToken)) {
                deleteRefreshToken(refreshToken);
                claims = null;
            }
        } catch (JwtException | IllegalArgumentException e) {
            claims = null;
        }
        return claims;
    }

    public void saveRefreshToken(String refreshToken, String email) {
        log.info("saveRefreshToken"+refreshToken+" "+email);
        redisTemplate.opsForValue().set(email, refreshToken, REFRESH_TOKEN_EXP, java.util.concurrent.TimeUnit.MILLISECONDS);
        log.info("refreshToken saved");


    }

    public void deleteRefreshToken(String email) {
        redisTemplate.delete(email);
    }
}
