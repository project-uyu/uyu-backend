package uyu.server.folder.web.dto.response;

import java.util.List;
import lombok.Getter;
import uyu.server.folder.web.dto.FolderDTO;

@Getter
public class SearchFolderResponseDTO {
    private Long linkNum;
    private List<FolderDTO> src;

}
