package uyu.server.link.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class LinkRequestDto {
    private String url;
    private String content;
    private List<TagListResponseDto> tagLists;
}
