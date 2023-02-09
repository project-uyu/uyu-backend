package uyu.server.link.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.link.data.entity.Link;
import uyu.server.link.data.repository.LinkRepository;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.linkTag.data.repository.LinkTagRepository;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final LinkTagRepository linkTagRepository;
    @Override
    public LinkResponseDto getLinkDetail(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId));
        List<TagListResponseDto> tagLists = linkTagRepository.findByIdUsingFetchJoinTag(linkId)
                .stream().map(tag -> new TagListResponseDto(tag)).collect(Collectors.toList());
        return LinkResponseDto.builder()
                .content(link.getContent())
                .createdDate(link.getCreatedDate())
                .hit(link.getHit())
                .modifiedDate(link.getModifiedDate())
                .tagLists(tagLists)
                .build();
    }

    @Override
    @Transactional
    public Long modifyLink(Long linkId, LinkRequestDto requestDto) {
        Link link = linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId));
        return null;
    }

    @Override
    @Transactional
    public Long deleteLink(Long linkId) {
        return null;
    }
}
