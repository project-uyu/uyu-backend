package uyu.server.link.web.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import uyu.server.link.data.entity.Link;
import uyu.server.tag.web.dto.TagListResponseDto;

@Getter
public class LinkSearchResponseDto {
    private Long linkId;
    private String title;
    private List<TagListResponseDto> tagLists;
    private LocalDate modifiedDate;

    @Builder
    public LinkSearchResponseDto(Long linkId, String title, List<TagListResponseDto> tagLists,
        LocalDate modifiedDate) {
        this.linkId = linkId;
        this.title = title;
        this.tagLists = tagLists;
        this.modifiedDate = modifiedDate;
    }

    public LinkSearchResponseDto(Link link, List<TagListResponseDto> tagLists) {
        this.linkId = link.getId();
        this.title = link.getTitle();
        this.tagLists = tagLists;
        this.modifiedDate = link.getModifiedDate();
    }
}
