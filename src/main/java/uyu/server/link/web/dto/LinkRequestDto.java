package uyu.server.link.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uyu.server.link.data.entity.Link;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class LinkRequestDto {
    private String url;
    private String title;
    private String content;
    private List<TagListResponseDto> tagLists;
    @Builder
    public Link toEntity() {
        return Link.builder()
                .title(title)
                .content(content)
                .url(url)
                .build();
    }
}
