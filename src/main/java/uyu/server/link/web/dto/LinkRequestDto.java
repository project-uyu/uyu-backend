package uyu.server.link.web.dto;

import lombok.Getter;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@Getter
public class LinkRequestDto {
    private String url;
    private String content;
    private List<TagListResponseDto> tagLists;
}
