package uyu.server.tag.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uyu.server.tag.data.entity.Tag;
import uyu.server.tag.service.TagService;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TagApiController.class)
public class TagControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private TagService tagService;
    Tag tag;
    List<TagListResponseDto> dtoList = new ArrayList<>();
    @BeforeEach
    void settings() {
        tag = new Tag("Spring");
        tag.setId(1L);

        dtoList.add(new TagListResponseDto(tag));
    }

    @Test
    @DisplayName("태그를 생성할 수 있다.")
    void createTag() throws Exception {
        when(tagService.createTag(any())).thenReturn(tag.getId());

        mockMvc.perform(post("/tags/new")
                .param("name",tag.getName()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("태그 리스트를 반환할 수 있다.")
    void getTagList() throws Exception {
        when(tagService.getTagList()).thenReturn(dtoList);

        mockMvc.perform(get("/tags/list")
                        .param("name",tag.getName()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("태그를 수정할 수 있다.")
    void modifyTag() throws Exception {
        when(tagService.modifyTag(any(), any())).thenReturn(tag.getId());

        mockMvc.perform(patch("/tags/1")
                        .param("name","spring2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("태그를 삭제할 수 있다.")
    void deleteTag() throws Exception {
        when(tagService.deleteTag(any())).thenReturn(tag.getId());

        mockMvc.perform(delete("/tags/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}