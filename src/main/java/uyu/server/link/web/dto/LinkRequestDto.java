package uyu.server.link.web.dto;

import lombok.Builder;
import lombok.Getter;
import uyu.server.link.data.entity.Link;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@Getter
public class LinkRequestDto {
    private String url;
    private String content;
    private List<TagListResponseDto> tagLists;
    @Builder
    public Link toEntity() {
        return Link.builder()
                .content(content)
                .url(url)
                .build();
    }
}
