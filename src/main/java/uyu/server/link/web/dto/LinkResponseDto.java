package uyu.server.link.web.dto;

import lombok.Builder;
import lombok.Getter;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.time.LocalDate;
import java.util.List;

@Getter
public class LinkResponseDto {
    private String url;
    private String content;
    private List<TagListResponseDto> tagLists;
    private Long hit;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    @Builder
    public LinkResponseDto(String url, String content, List<TagListResponseDto> tagLists, Long hit, LocalDate createdDate, LocalDate modifiedDate) {
        this.url = url;
        this.content = content;
        this.tagLists = tagLists;
        this.hit = hit;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
