package uyu.server.folder.web.dto.response;

import lombok.Getter;

@Getter
public class SearchFolderResponseDTO {
    private Long linkNum;
    private SearchFolderDto src;

    public SearchFolderResponseDTO(Long linkNum, SearchFolderDto src) {
        this.linkNum = linkNum;
        this.src = src;
    }
}
