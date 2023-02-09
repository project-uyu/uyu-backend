package uyu.server.link.web;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;

@RestController
@RequestMapping("/links/")
@RequiredArgsConstructor
public class LinkApiController {
    private final LinkService linkService;

    @GetMapping("{linkId}")
    public ResponseEntity<LinkResponseDto> getLinkDetail(@PathVariable @NotNull(message="필수값입니다.") Long linkId) {
        LinkResponseDto linkDetail = linkService.getLinkDetail(linkId);
        return ResponseEntity.status(HttpStatus.OK).body(linkDetail);
    }

    @PatchMapping("{linkId}")
    public ResponseEntity<Long> modifyLink(@PathVariable @NotNull(message="필수값입니다.") Long linkId, @RequestBody LinkRequestDto linkDto) {
        Long modifyLink = linkService.modifyLink(linkId, linkDto);
        return ResponseEntity.status(HttpStatus.OK).body(modifyLink);
    }

    @DeleteMapping("{linkId}")
    public ResponseEntity<Long> deleteTag(@PathVariable @NotNull(message="필수값입니다.") Long linkId) {
        Long deleteLink = linkService.deleteLink(linkId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteLink);
    }
}
