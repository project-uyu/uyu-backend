package uyu.server.tag.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.tag.data.entity.Tag;
import uyu.server.tag.data.repository.TagRepository;
import uyu.server.tag.service.TagService;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public Long createTag(String name) {
        return tagRepository.save(Tag.builder()
                .name(name)
                .build()).getId();
    }

    @Override
    public List<TagListResponseDto> getTagList() {
        return tagRepository.findAll()
                .stream()
                .map(tag -> new TagListResponseDto(tag)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long deleteTag(Long id) {
        tagRepository.delete(tagRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 tag가 없습니다!" + id)));
        return id;
    }

    @Override
    @Transactional
    public Long modifyTag(Long id, String name) {
        Tag tag = tagRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 tag가 없습니다!" + id));
        tag.setName(name);
        return tag.getId();
    }
}
