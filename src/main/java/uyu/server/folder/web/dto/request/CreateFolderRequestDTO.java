package uyu.server.folder.web.dto.request;

import lombok.Data;

@Data
public class CreateFolderRequestDTO {
    String title;
    Long parentFolderId;
}
