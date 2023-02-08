package uyu.server.tag.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uyu.server.tag.service.TagService;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    @Override
    public Long createTag(String name) {
        return null;
    }

    @Override
    public List<TagListResponseDto> getTagList() {
        return null;
    }

    @Override
    public Long deleteTag(Long id) {
        return null;
    }

    @Override
    public Long modifyTag(Long id, String name) {
        return null;
    }
}
