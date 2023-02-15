package uyu.server.tag.web.dto;

import lombok.Builder;
import lombok.Getter;
import uyu.server.tag.repository.entity.Tag;

@Getter
public class TagListResponseDto {
    Long id;
    String name;

    public TagListResponseDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    @Builder
    public Tag toEntity() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
