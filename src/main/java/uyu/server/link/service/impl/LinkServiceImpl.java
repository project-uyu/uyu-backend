package uyu.server.link.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.folder.data.entity.Folder;
import uyu.server.folder.data.repository.FolderRepository;
import uyu.server.link.data.entity.Link;
import uyu.server.link.data.repository.LinkRepository;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.linkTag.data.entity.LinkTag;
import uyu.server.linkTag.data.repository.LinkTagRepository;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final FolderRepository folderRepository;
    private final LinkTagRepository linkTagRepository;
    @Override
    public LinkResponseDto getLinkDetail(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 링크가 존재하지 않습니다."+linkId));
        List<TagListResponseDto> tagLists = linkTagRepository.findTagsByLinkIdUsingFetchJoinTag(linkId)
                .stream().map(tag -> new TagListResponseDto(tag)).collect(Collectors.toList());
        return LinkResponseDto.builder()
                .linkId(linkId)
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
    @Transactional
    @Override
    public Long createLink(Long folderId, LinkRequestDto linkDto) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 폴더가 없습니다." + folderId));
        //링크 생성
        Link link = linkRepository.save(Link.builder()
                        .url(linkDto.getUrl())
                        .content(linkDto.getContent())
                        .folder(folder)
                .build());
        //링크 태그 생성
        linkDto.getTagLists().stream().forEach(tagDto -> linkTagRepository.save(LinkTag.builder().link(link).tag(tagDto.toEntity()).build()));
        return link.getId();
    }

    @Override
    public List<LinkResponseDto> getLinkList(Long folderId) {
        List<LinkResponseDto> linkResponseDtos = new ArrayList<>();
        Folder folder = folderRepository.findById(folderId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 폴더가 없습니다." + folderId));

        // 폴더 내부의 링크 정보와 연결
        folder.getLinks().forEach(link -> {
            List<TagListResponseDto> tagListResponseDtos = new ArrayList<>();

            // 링크 내부의 태그 정보와 연결
            linkTagRepository.findTagsByLinkIdUsingFetchJoinTag(link.getId())
                    .forEach(tag -> tagListResponseDtos.add(new TagListResponseDto(tag)));

            linkResponseDtos.add(new LinkResponseDto(link, tagListResponseDtos));
        });

        return linkResponseDtos;
    }
}
