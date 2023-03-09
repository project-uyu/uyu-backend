package uyu.server.folder.web.dto;

import lombok.Data;

import java.util.List;
import uyu.server.folder.data.entity.Folder;

@Data
public class FolderDTO {
    Long folderId;
    Long parentFolderId;
    String folderTitle;
    List<FolderDTO> children_folders;
}
