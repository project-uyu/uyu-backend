package uyu.server.folder.data.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.folder.data.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder,Long> {
  Optional<List<Folder>> findByTitleContaining(String title);
}
