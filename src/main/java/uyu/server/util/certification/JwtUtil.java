package uyu.server.util.certification;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uyu.server.member.data.entity.Member;

import java.sql.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private String secretKey = "secretKey";
    private Long exp = 1000L * 60 * 60;


    //토큰 생성
    public String createToken(Member member) { // 토큰에 담고싶은 값 파라미터로 가져오기
        return Jwts.builder()
                .setHeaderParam("typ", "JWT") // 토큰 타입
                .setSubject("userToken") // 토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + exp)) // 토큰 유효시간
                .claim("role", member.getId()) // 토큰에 담을 데이터
                .claim("email",member.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // secretKey를 사용하여 해싱 암호화 알고리즘 처리
                .compact(); // 직렬화, 문자열로 변경
    }

    // 토큰에서 회원 정보 추출
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    // 토큰 유효성 검사
    public Claims validateToken(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
    }

}
