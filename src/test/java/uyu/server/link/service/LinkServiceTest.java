package uyu.server.link.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uyu.server.link.service.impl.LinkServiceImpl;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class LinkServiceTest {
    @Mock
    private LinkServiceImpl linkService;
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
}
