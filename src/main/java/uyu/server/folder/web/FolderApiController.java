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

import java.util.List;

@RestController
@RequestMapping("/folders/")
@RequiredArgsConstructor
public class FolderApiController {


    private final FolderService folderService;

    @GetMapping()
    public ResponseEntity<List<FolderDTO>> getFolderList(@RequestBody CreateFolderRequestDTO createFolderDTO) {
        Long userId = null;
        List<FolderDTO> folderList = folderService.getFolderList(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(folderList);
    }

    @PostMapping("new")
    public ResponseEntity<Long> createFolder(@RequestBody CreateFolderRequestDTO createFolderDTO) {
        Long newFolderId = folderService.createNewFolder(createFolderDTO.getTitle(),createFolderDTO.getParentFolderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(newFolderId);
    }

    @DeleteMapping("{folderId}")
    public ResponseEntity<Long> deleteFolder(@PathVariable @NotNull(message="필수값입니다.") Long folderId) {
        Long deleteFolderId = folderService.deleteFolder(folderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteFolderId);
    }

    @DeleteMapping("{folderId}/links")
    public ResponseEntity<Long> deleteFolderLink(@PathVariable @NotNull(message="필수값입니다.") Long folderId) {
        Long deleteFolderId = folderService.deleteFolderLinks(folderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteFolderId);
    }

    @PatchMapping("{folderId}")
    public ResponseEntity<Long> modifyFolder(@PathVariable @NotNull(message="필수값입니다.")
                                                 @RequestBody ModifyFolderRequestDTO modifyFolderDTO) {
        Long modifyFolderId = folderService.modifyFolder();
        return ResponseEntity.status(HttpStatus.CREATED).body(modifyFolderId);
    }




}
