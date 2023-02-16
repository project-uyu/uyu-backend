package uyu.server.link.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uyu.server.folder.data.entity.Folder;
import uyu.server.folder.data.repository.FolderRepository;
import uyu.server.link.data.entity.Link;
import uyu.server.link.data.repository.LinkRepository;
import uyu.server.link.service.impl.LinkServiceImpl;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.linkTag.data.entity.LinkTag;
import uyu.server.linkTag.data.repository.LinkTagRepository;
import uyu.server.tag.data.entity.Tag;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class LinkServiceTest {
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private FolderRepository folderRepository;
    @Mock
    private LinkTagRepository linkTagRepository;
    @InjectMocks
    private LinkServiceImpl linkService;

    LinkResponseDto responseDto;
    LinkRequestDto requestDto;
    List<TagListResponseDto> dtoList = new ArrayList<>();
    Folder folder;
    Link link;
    Tag tag;
    LinkTag linkTag;
    List<Tag> tags= new ArrayList<>();
    List<LinkTag> linkTags = new ArrayList<>();
    @BeforeEach
    void settings() {
        tag = new Tag("Spring");
        tag.setId(1L);

        folder = Folder.builder()
                .title("폴더 이름")
                .build();
        folder.setId(1L);

        link = Link.builder()
                .url("https://github.com/project-uyu/uyu-backend")
                .content("링크 내용")
                .folder(folder)
                .build();
        link.setId(1L);

        linkTag = LinkTag.builder()
                .link(link)
                .tag(tag)
                .build();

        dtoList.add(new TagListResponseDto(tag));

        responseDto = new LinkResponseDto(link, dtoList);

        requestDto = new LinkRequestDto(link.getUrl(), link.getContent(), dtoList);

        tags.add(tag);

        linkTags.add(linkTag);
    }

    @Test
    @DisplayName("링크를 생성할 수 있다.")
    void createLink() {
        //mocking
        given(folderRepository.findById(any())).willReturn(Optional.ofNullable(folder));
        given(linkRepository.save(any())).willReturn(link);

        //when
        Long linkId = linkService.createLink(folder.getId(),requestDto);

        //then
        assertEquals((Optional.ofNullable(linkId)), Optional.ofNullable(1L));
    }

    @Test
    @DisplayName("링크 세부 내용을 알 수 있다.")
    void getLinkDetail() {
        //mocking
        given(linkRepository.findById(any())).willReturn(Optional.ofNullable(link));
        given(linkTagRepository.findTagsByLinkIdUsingFetchJoinTag(any())).willReturn(tags);

        //when
        LinkResponseDto linkResponseDto = linkService.getLinkDetail(link.getId());

        //then
        assertEquals(linkResponseDto.getLinkId(), link.getId());
        assertEquals(linkResponseDto.getContent(), link.getContent());
        assertEquals(linkResponseDto.getCreatedDate(), link.getCreatedDate());
        assertEquals(linkResponseDto.getHit(), link.getHit());
        assertEquals(linkResponseDto.getModifiedDate(), link.getModifiedDate());
        assertEquals(linkResponseDto.getTagLists().get(0).getName(), new TagListResponseDto(tags.get(0)).getName());
    }

    @Test
    @DisplayName("링크를 수정할 수 있다.")
    void modifyLink() {
        //mocking
        given(linkRepository.findById(any())).willReturn(Optional.ofNullable(link));
        given(linkTagRepository.findTagIdsByLinkId(any())).willReturn(new ArrayList<>(Arrays.asList(1L)));
        given(linkTagRepository.save(any())).willReturn(linkTag);

        //when
        Long linkId = linkService.modifyLink(link.getId(), requestDto);

        //then
        assertEquals(linkId, link.getId());
    }

    @Test
    @DisplayName("링크를 삭제할 수 있다.")
    void deleteLink() {
        //mocking
        given(linkTagRepository.findLinkTagsByLinkId(any())).willReturn(linkTags);
        given(linkRepository.findById(any())).willReturn(Optional.ofNullable(link));

        //when
        Long linkId = linkService.deleteLink(link.getId());

        //then
        assertEquals(linkId, link.getId());
    }
}
