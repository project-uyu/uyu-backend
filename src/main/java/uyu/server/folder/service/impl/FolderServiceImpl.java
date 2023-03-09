package uyu.server.folder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyu.server.folder.data.entity.Folder;
import uyu.server.folder.data.repository.FolderRepository;
import uyu.server.folder.service.FolderService;
import uyu.server.folder.web.dto.FolderDTO;

import java.util.List;
import uyu.server.folder.web.dto.response.SearchFolderResponseDTO;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public Long createNewFolder(String title, Long folderId) {
        return folderRepository.save(Folder.builder()
                .title(title)
                .parentFolder(folderId == null ? null :
                        folderRepository.findById(folderId)
                                .orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 폴더가 없습니다" + folderId)))
                .build())
                .getId();
    }

    @Override
    public Long deleteFolder(Long folderId) {
        folderRepository.delete(folderRepository.findById(folderId).orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 폴더가 없습니다" + folderId)));
        return folderId;
    }

    @Override
    public Long deleteFolderLinks(Long folderId) {
        return null;
    }

    @Transactional
    @Override
    public Long modifyFolder(Long folderId, String title, Long parentFolderId) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 폴더가 없습니다" + folderId));

        System.out.println(folderId+" "+ folder);
        folder.update(title, parentFolderId == null ? null :
                folderRepository.findById(parentFolderId)
                        .orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 부모 폴더가 없습니다" + parentFolderId)));


        return folder.getId();
    }

    @Override
    public List<FolderDTO> getFolderList(Long userId) {
        return null;
    }

    @Override
    public List<SearchFolderResponseDTO> searchFolder(String word) {
        return null;
    }
}
