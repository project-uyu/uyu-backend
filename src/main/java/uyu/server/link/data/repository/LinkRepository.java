package uyu.server.link.data.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.link.data.entity.Link;

public interface LinkRepository extends JpaRepository<Link,Long> {
  Optional<List<Link>> findByTitleContaining(String title);

}
