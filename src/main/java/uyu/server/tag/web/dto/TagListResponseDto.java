package uyu.server.tag.web.dto;

import uyu.server.tag.data.entity.Tag;

public class TagListResponseDto {
    Long id;
    String name;

    public TagListResponseDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
