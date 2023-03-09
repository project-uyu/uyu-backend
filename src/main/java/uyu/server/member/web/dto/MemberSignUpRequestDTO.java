package uyu.server.member.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uyu.server.link.data.entity.Link;
import uyu.server.member.data.entity.Member;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberSignUpRequestDTO {
    private String email;
    private String password;
    private String name;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
