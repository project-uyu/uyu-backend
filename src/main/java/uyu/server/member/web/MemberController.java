package uyu.server.member.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.member.data.entity.Member;
import uyu.server.member.service.MemberService;
import uyu.server.member.web.dto.MemberLoginRequestDTO;
import uyu.server.member.web.dto.MemberSignUpRequestDTO;
import uyu.server.util.certification.JwtUtil;

import javax.naming.AuthenticationException;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

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
    public ResponseEntity<String> login(@RequestBody MemberLoginRequestDTO memberLoginRequest) throws Exception {
        try {
            Member member = memberService.authenticate(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
            String token = jwtUtil.createToken(member);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + token);
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
