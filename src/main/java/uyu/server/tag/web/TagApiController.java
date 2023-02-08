package uyu.server.tag.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags/")
@RequiredArgsConstructor
public class TagApiController {
    @PostMapping("new")
    public ResponseEntity<Long> createTag(@RequestParam(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body();
    }

    @GetMapping("list")
    public ResponseEntity<> getTagList() {
        return ResponseEntity.status(HttpStatus.OK).body();
    }

    @PatchMapping("{tagId}")
    public ResponseEntity<Long> modifyTag(@RequestParam(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body();
    }

    @DeleteMapping("{tagId}")
    public ResponseEntity<Long> deleteTag() {
        return ResponseEntity.status(HttpStatus.OK).body();
    }
}
