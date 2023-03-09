package uyu.server.folder.web;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyu.server.folder.service.FolderService;
import uyu.server.folder.web.dto.request.CreateFolderRequestDTO;
import uyu.server.folder.web.dto.FolderDTO;
import uyu.server.folder.web.dto.request.ModifyFolderRequestDTO;
import uyu.server.folder.web.dto.request.SearchFolderRequestDTO;
import uyu.server.link.service.LinkService;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.link.web.dto.LinkResponseDto;

import java.util.List;

@RestController
@RequestMapping("/folders/")
@RequiredArgsConstructor
public class FolderApiController {
    private final FolderService folderService;
    private final LinkService linkService;

    @GetMapping()
    public ResponseEntity<List<FolderDTO>> getFolderList(@RequestBody CreateFolderRequestDTO createFolderDTO) {
        Long userId = null;
        List<FolderDTO> folderList = folderService.getFolderList(userId);
        return ResponseEntity.status(HttpStatus.OK).body(folderList);
    }

    @PostMapping("new")
    public ResponseEntity<Long> createFolder(@RequestBody CreateFolderRequestDTO createFolderDTO) {
        Long newFolderId = folderService.createNewFolder(createFolderDTO.getTitle(),createFolderDTO.getParentFolderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(newFolderId);
    }

    @DeleteMapping("{folderId}")
    public ResponseEntity<Long> deleteFolder(@PathVariable @NotNull(message="필수값입니다.") Long folderId) {
        Long deleteFolderId = folderService.deleteFolder(folderId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteFolderId);
    }

    @DeleteMapping("{folderId}/links")
    public ResponseEntity<Long> deleteFolderLink(@PathVariable @NotNull(message="필수값입니다.") Long folderId) {
        Long deleteFolderId = folderService.deleteFolderLinks(folderId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteFolderId);
    }

    @PatchMapping("{folderId}")
    public ResponseEntity<Long> modifyFolder(@PathVariable @NotNull(message="필수값입니다.") Long folderId,
                                                 @RequestBody ModifyFolderRequestDTO modifyFolderDTO) {

        Long modifyFolderId = folderService.modifyFolder(folderId, modifyFolderDTO.getTitle(), modifyFolderDTO.getParentFolderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(modifyFolderId);
    }

    @GetMapping("search")
    public ResponseEntity<List<SearchFolderRequestDTO>> searchFolder(@RequestParam(value = "word") String word) {
        List<SearchFolderRequestDTO> searchList = folderService.searchFolder(word);
        return ResponseEntity.status(HttpStatus.OK).body(searchList);
    }

    @PostMapping("{folderId}/links/new")
    public ResponseEntity<Long> createLink(@PathVariable @NotNull(message="필수값입니다.") Long folderId,
                                           @RequestBody LinkRequestDto dto) {
        Long newLink = linkService.createLink(folderId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLink);
    }

    @GetMapping("{folderId}/links")
    public ResponseEntity<List<LinkResponseDto>> getLinkList(@PathVariable @NotNull(message="필수값입니다.") Long folderId) {
        List<LinkResponseDto> dtoList = linkService.getLinkList(folderId);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

}
