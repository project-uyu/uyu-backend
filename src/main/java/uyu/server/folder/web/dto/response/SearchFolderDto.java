package uyu.server.folder.web.dto.response;

import lombok.Getter;
import uyu.server.folder.data.entity.Folder;

@Getter
public class SearchFolderDto {
    Long folderId;
    Long parentFolderId;
    String folderTitle;
    SearchFolderDto childrenFolder;
    public SearchFolderDto(Folder folder, SearchFolderDto folderDTO) {
      this.folderId = folder.getId();
      this.parentFolderId = folder.getParentFolder().getId();
      this.folderTitle = folder.getTitle();
      this.childrenFolder = folderDTO;
    }
}
