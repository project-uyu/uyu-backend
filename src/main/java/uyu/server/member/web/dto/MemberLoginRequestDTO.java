package uyu.server.member.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uyu.server.member.data.entity.Member;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDTO {
    private String email;
    private String password;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .password(password)
                .email(email)
                .build();
    }
}
