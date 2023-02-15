package uyu.server.link.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.link.repository.entity.Link;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
