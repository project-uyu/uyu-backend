package uyu.server.tag.web.dto;

import lombok.Getter;
import uyu.server.tag.data.entity.Tag;

@Getter
public class TagListResponseDto {
    Long id;
    String name;

    public TagListResponseDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
