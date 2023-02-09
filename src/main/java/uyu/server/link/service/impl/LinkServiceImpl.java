package uyu.server.link.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.link.data.entity.Link;
import uyu.server.link.data.repository.LinkRepository;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.linkTag.data.entity.LinkTag;
import uyu.server.linkTag.data.repository.LinkTagRepository;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        List<TagListResponseDto> tagLists = linkTagRepository.findTagsByLinkIdUsingFetchJoinTag(linkId)
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
        //링크 수정
        Link newLink = linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId)).update(requestDto);

        //기존 태그와 비교
        Set<Long> tags = new HashSet<>(linkTagRepository.findTagIdsByLinkId(linkId));
        List<TagListResponseDto> tagLists = requestDto.getTagLists();
        Iterator<TagListResponseDto> it = tagLists.iterator();
        while(it.hasNext()) {
            TagListResponseDto dto = it.next();
            if(tags.contains(dto.getId())) {
                tags.remove(dto.getId());
            } else {
                //없으면 생성
                linkTagRepository.save(LinkTag.builder()
                                .link(newLink)
                                .tag(dto.toEntity()).build());
            }
        }

        // 남은 애들 삭제
        Iterator<Long> setIt = tags.iterator();
        while(setIt.hasNext()) {
            linkTagRepository.delete(linkTagRepository.findLinkTagByLinkIdAndTagId(newLink.getId(), setIt.next()));
        }
        return newLink.getId();
    }

    @Override
    @Transactional
    public Long deleteLink(Long linkId) {
        // 링크태그 지우기
        List<LinkTag> linkTags = linkTagRepository.findLinkTagsByLinkId(linkId);
        Iterator<LinkTag> it = linkTags.iterator();
        while(it.hasNext()) {
            linkTagRepository.delete(it.next());
        }

        //링크 지우기
        linkRepository.delete(linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId)));
        return linkId;
    }
}
