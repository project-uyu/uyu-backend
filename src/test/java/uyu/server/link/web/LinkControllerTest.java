package uyu.server.link.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uyu.server.folder.data.entity.Folder;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.tag.repository.entity.Tag;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LinkApiController.class)
public class LinkControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private LinkService linkService;
    @Autowired
    private ObjectMapper objectMapper;
    LinkResponseDto responseDto;
    LinkRequestDto requestDto;
    List<TagListResponseDto> dtoList;
    @BeforeEach
    void settings() {
        dtoList = new LinkedList<>();
        dtoList.add(new TagListResponseDto(1L,"태그 이름"));
        responseDto = LinkResponseDto.builder()
                .hit(1L)
                .modifiedDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .content("링크 내용")
                .url("https://github.com/project-uyu/uyu-backend")
                .tagLists(dtoList)
                .build();
        requestDto = new LinkRequestDto("https://github.com/project-uyu/uyu-backend","링크 내용",dtoList);
    }

    @Test
    @DisplayName("링크에 대한 정보를 얻을 수 있다.")
    void getLinkDetail() throws Exception {
        when(linkService.getLinkDetail(any())).thenReturn(responseDto);

        mockMvc.perform(get("/links/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("링크에 대한 정보를 수정할 수 있다.")
    void modifyLinkDetail() throws Exception {
        when(linkService.modifyLink(any(),any())).thenReturn(1L);
        String content = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(patch("/links/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("링크를 삭제할 수 있다.")
    void deleteLink() throws Exception {
        when(linkService.deleteLink(any())).thenReturn(1L);
        mockMvc.perform(delete("/links/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
