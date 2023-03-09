package uyu.server.member.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.member.service.MemberService;
import uyu.server.member.web.dto.MemberSignUpRequestDTO;
import uyu.server.util.Permission;
import uyu.server.util.PermissionRole;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/admin")
    @Permission(role = PermissionRole.ADMIN)
    public String adminPage(){
        return "admin";
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpRequestDTO memberSignUpRequest){
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

}
