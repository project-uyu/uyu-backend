package uyu.server.folder.web.dto.request;

import java.util.List;
import lombok.Getter;
import uyu.server.folder.web.dto.FolderDTO;

@Getter
public class SearchFolderRequestDTO {
    private Long linkNum;
    private List<FolderDTO> src;

}
