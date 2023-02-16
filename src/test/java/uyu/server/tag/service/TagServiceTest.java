package uyu.server.tag.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uyu.server.tag.data.entity.Tag;
import uyu.server.tag.data.repository.TagRepository;
import uyu.server.tag.service.impl.TagServiceImpl;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TagServiceTest {
    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private TagServiceImpl tagService;
    Tag tag;
    List<Tag> tagList = new ArrayList<>();
    List<TagListResponseDto> dtoList = new ArrayList<>();
    @BeforeEach
    void settings() {
        tag = new Tag("Spring");
        tag.setId(1L);

        tagList.add(tag);
        dtoList.add(new TagListResponseDto(tag));
    }

    @Test
    @DisplayName("링크를 생성할 수 있다.")
    void createLink() {
        //mocking
        given(tagRepository.save(any())).willReturn(tag);

        //when
        Long tagId = tagService.createTag(tag.getName());

        //then
        assertEquals(tagId, tag.getId());
    }

    @Test
    @DisplayName("태그를 수정할 수 있다.")
    void modifyTag() {
        //mocking
        given(tagRepository.findById(any())).willReturn(Optional.ofNullable(tag));

        //when
        Long tagId = tagService.modifyTag(tag.getId(), tag.getName());

        //then
        assertEquals(tagId, tag.getId());
    }

    @Test
    @DisplayName("태그를 삭제할 수 있다.")
    void deleteTag() {
        //mocking
        given(tagRepository.findById(any())).willReturn(Optional.ofNullable(tag));

        //when
        Long tagId = tagService.deleteTag(tag.getId());

        //then
        assertEquals(tagId, tag.getId());
    }

    @Test
    @DisplayName("태그리스트를 가져올 수 있다.")
    void getTagList() {
        //mocking
        given(tagRepository.findAll()).willReturn(tagList);

        //when
        dtoList = tagService.getTagList();

        //then
        assertEquals(dtoList.get(0).getId(), tag.getId());
    }

}
