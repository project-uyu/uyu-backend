package uyu.server.link.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uyu.server.link.data.entity.Link;
import uyu.server.link.data.repository.LinkRepository;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    @Override
    public LinkResponseDto getLinkDetail(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId));

        return LinkResponseDto.builder()
                .content(link.getContent())
                .createdDate(link.getCreatedDate())
                .hit(link.)
                .build();
    }

    @Override
    public Long modifyLink(Long linkId, LinkRequestDto requestDto) {
        return null;
    }

    @Override
    public Long deleteLink(Long linkId) {
        return null;
    }
}
