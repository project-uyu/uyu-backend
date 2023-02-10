package uyu.server.folder.web.dto;

import lombok.Data;

@Data
public class CreateFolderDTO {
    String title;
    Long parentFolderId;
}
