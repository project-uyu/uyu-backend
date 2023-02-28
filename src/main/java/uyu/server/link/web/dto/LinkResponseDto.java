package uyu.server.link.web.dto;

import lombok.Builder;
import lombok.Getter;
import uyu.server.link.data.entity.Link;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.time.LocalDate;
import java.util.List;

@Getter
public class LinkResponseDto {
    private Long linkId;
    private String title;
    private String url;
    private String content;
    private List<TagListResponseDto> tagLists;
    private Long hit;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    @Builder
    public LinkResponseDto(Long linkId, String title, String url, String content, List<TagListResponseDto> tagLists, Long hit, LocalDate createdDate, LocalDate modifiedDate) {
        this.linkId = linkId;
        this.url = url;
        this.title = title;
        this.content = content;
        this.tagLists = tagLists;
        this.hit = hit;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public LinkResponseDto(Link link, List<TagListResponseDto> tagLists) {
        this.linkId = link.getId();
        this.url = link.getUrl();
        this.title = link.getTitle();
        this.content = link.getContent();
        this.tagLists = tagLists;
        this.hit = link.getHit();
        this.createdDate = link.getCreatedDate();
        this.modifiedDate = link.getModifiedDate();
    }
}
