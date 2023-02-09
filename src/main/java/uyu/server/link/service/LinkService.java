package uyu.server.link.service;

import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;

public interface LinkService {
    LinkResponseDto getLinkDetail(Long linkId);
    Long modifyLink(Long linkId, LinkRequestDto requestDto);
    Long deleteLink(Long linkId);
}
