package uyu.server.folder.web.dto.request;

import lombok.Data;

@Data
public class ModifyFolderRequestDTO {
    String title;
    Long parentFolderId;
}