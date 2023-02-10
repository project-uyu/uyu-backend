package uyu.server.folder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uyu.server.folder.data.entity.Folder;
import uyu.server.folder.data.repository.FolderRepository;
import uyu.server.folder.service.FolderService;
import uyu.server.folder.web.dto.FolderDTO;
import uyu.server.tag.data.entity.Tag;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    @Override
    public Long createNewFolder(String title, Long folderId) {
        Optional<Folder> folder = folderRepository.findById(folderId);
        return folderRepository.save(Folder.builder().title(title).parentFolder(folder.get()).build()).getId();
    }

    @Override
    public Long deleteFolder(Long folderId) {
        return null;
    }

    @Override
    public Long deleteFolderLinks(Long folderId) {
        return null;
    }

    @Override
    public Long modifyFolder() {
        return null;
    }

    @Override
    public List<FolderDTO> getFolderList(Long userId) {
        return null;
    }
}
