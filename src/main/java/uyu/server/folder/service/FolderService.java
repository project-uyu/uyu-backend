package uyu.server.folder.service;

import uyu.server.folder.web.dto.FolderDTO;

import java.util.List;

public interface FolderService {
    Long createNewFolder(String name);

    Long deleteFolder(Long folderId);

    Long deleteFolderLinks(Long folderId);

    Long modifyFolder();

    List<FolderDTO> getFolderList(Long userId);
}
