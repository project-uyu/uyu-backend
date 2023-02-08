package uyu.server.link.web;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.link.service.LinkService;

@RestController
@RequestMapping("/links/")
@RequiredArgsConstructor
public class LinkApiController {
    private final LinkService linkService;

    @GetMapping("{linkId}")
    public ResponseEntity<List<TagListResponseDto>> getLinkDetail() {
        List<TagListResponseDto> tagLists = tagService.getTagList();
        return ResponseEntity.status(HttpStatus.OK).body(tagLists);
    }

    @PatchMapping("{linkId}")
    public ResponseEntity<Long> modifyTag(@PathVariable @NotNull(message="필수값입니다.") Long linkId, @RequestParam(value = "name") String name) {
        Long modifyTag = tagService.modifyTag(tagId, name);
        return ResponseEntity.status(HttpStatus.OK).body(modifyTag);
    }

    @DeleteMapping("{linkId}")
    public ResponseEntity<Long> deleteTag(@PathVariable @NotNull(message="필수값입니다.") Long linkId) {
        Long deleteTag = tagService.deleteTag(tagId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteTag);
    }
}
