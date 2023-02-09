package uyu.server.link.web.dto;

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
}
