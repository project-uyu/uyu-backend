package uyu.server.member.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.member.data.entity.Member;
import uyu.server.member.service.MemberService;
import uyu.server.member.web.dto.MemberLoginRequestDTO;
import uyu.server.member.web.dto.MemberSignUpRequestDTO;
import uyu.server.util.certification.JwtUtil;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";

    private static final String REFRESH_TOKEN_PREFIX = "Refresh ";

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @GetMapping("/admin")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("admin page");
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpRequestDTO memberSignUpRequest) {
        memberService.signUp(memberSignUpRequest.getEmail(),
                memberSignUpRequest.getName(),
                memberSignUpRequest.getPassword());

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberLoginRequestDTO memberLoginRequest) throws Exception {
        try {
            Member member = memberService.authenticate(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
            String jwtToken = jwtUtil.createAccessToken(member);
            String refreshToken = jwtUtil.createRefreshToken(member);
            jwtUtil.saveRefreshToken(refreshToken, member.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", jwtToken);
            response.put("refreshToken", refreshToken);
            return ResponseEntity.ok()
                    .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN_PREFIX + jwtToken)
                    .header(REFRESH_TOKEN_HEADER, REFRESH_TOKEN_PREFIX + refreshToken)
                    .body(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(REFRESH_TOKEN_HEADER) String refreshToken) {
        refreshToken = refreshToken.substring(REFRESH_TOKEN_PREFIX.length());
        jwtUtil.deleteRefreshToken(refreshToken);
        return ResponseEntity.ok("Logout successfully.");
    }

}
