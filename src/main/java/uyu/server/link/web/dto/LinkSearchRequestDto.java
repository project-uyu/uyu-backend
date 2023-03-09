package uyu.server.link.web.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import uyu.server.tag.web.dto.TagListResponseDto;

@Getter
public class LinkSearchRequestDto {
    private Long linkId;
    private String title;
    private List<TagListResponseDto> tagLists;
    private LocalDate modifiedDate;
}
