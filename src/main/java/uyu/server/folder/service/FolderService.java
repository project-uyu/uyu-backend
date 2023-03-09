package uyu.server.folder.service;

import uyu.server.folder.web.dto.FolderDTO;

import java.util.List;
import uyu.server.folder.web.dto.response.SearchFolderResponseDTO;

public interface FolderService {
    Long createNewFolder(String title, Long parentFolderId);

    Long deleteFolder(Long folderId);

    Long deleteFolderLinks(Long folderId);

    Long modifyFolder(Long folderId, String title, Long parentFolderId);

    List<FolderDTO> getFolderList(Long userId);
    List<SearchFolderResponseDTO> searchFolder(String word);

}
