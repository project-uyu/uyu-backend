package uyu.server.folder.web.dto;

import lombok.Data;

@Data
public class ModifyFolderDTO {
    String title;
    Long parentFolderId;
}
