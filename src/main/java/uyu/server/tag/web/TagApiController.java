package uyu.server.tag.web;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.tag.service.TagService;
import uyu.server.tag.web.dto.TagListResponseDto;

import java.util.List;

@RestController
@RequestMapping("/tags/")
@RequiredArgsConstructor
public class TagApiController {
    private final TagService tagService;
    @PostMapping("new")
    public ResponseEntity<Long> createTag(@RequestParam(value = "name") String name) {
        Long newTag = tagService.createTag();
        return ResponseEntity.status(HttpStatus.CREATED).body(newTag);
    }

    @GetMapping("list")
    public ResponseEntity<List<TagListResponseDto>> getTagList() {
        List<TagListResponseDto> tagLists = tagService.getTagList();
        return ResponseEntity.status(HttpStatus.OK).body(tagLists);
    }

    @PatchMapping("{tagId}")
    public ResponseEntity<Long> modifyTag(@PathVariable @NotNull(message="필수값입니다.") Long tagId, @RequestParam(value = "name") String name) {
        Long modifyTag = tagService.modifyTag(tagId, name);
        return ResponseEntity.status(HttpStatus.OK).body(modifyTag);
    }

    @DeleteMapping("{tagId}")
    public ResponseEntity<Long> deleteTag(@PathVariable @NotNull(message="필수값입니다.") Long tagId) {
        Long deleteTag = tagService.deleteTag(tagId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteTag);
    }
}
