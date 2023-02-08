package uyu.server.tag.service;

import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

public interface TagService {
    Long createTag();
    List<TagListResponseDto> getTagList();
    Long deleteTag(Long id);
    Long modifyTag(Long id, String name);
}
