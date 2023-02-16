package uyu.server.link.service;

import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;

import java.util.List;

public interface LinkService {
    LinkResponseDto getLinkDetail(Long linkId);
    Long modifyLink(Long linkId, LinkRequestDto requestDto);
    Long deleteLink(Long linkId);

    Long createLink(Long folderId, LinkRequestDto dto);

    List<LinkResponseDto> getLinkList(Long folderId);
}
