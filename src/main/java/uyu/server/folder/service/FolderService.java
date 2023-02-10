package uyu.server.folder.service;

import uyu.server.folder.web.dto.FolderListDTO;

public interface FolderService {
    Long createNewFolder(String name);

    Long deleteFolder(Long folderId);

    Long deleteFolderLinks(Long folderId);

    Long modifyFolder();

    FolderListDTO getFolderList(Long userId);
}
