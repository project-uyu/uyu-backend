package uyu.server.member.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
