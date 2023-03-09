package uyu.server.member.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.member.web.dto.MemberSignUpRequestDTO;
import uyu.server.util.Permission;
import uyu.server.util.PermissionRole;

@RestController
@RequestMapping("/api/users")
public class MemberController {

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
