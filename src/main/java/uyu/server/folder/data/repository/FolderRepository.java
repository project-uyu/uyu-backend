package uyu.server.folder.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.folder.data.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder,Long> {
}
