package uyu.server.tag.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.tag.data.entity.Tag;
import uyu.server.tag.data.repository.TagRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;
    Tag tag;
    @BeforeEach
    void settings() {
        tag = new Tag("Spring");
        tag.setId(1L);
    }

    @Test
    @DisplayName("태그정보 저장 후 찾기")
    void saveAndFind() {
        //when
        Long id = 1L;
        tagRepository.save(tag);
        Tag findTag = tagRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 태그가 없습니다." + id));

        //then
        assertEquals(id, findTag.getId());
    }
}
