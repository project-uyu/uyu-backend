package uyu.server.link.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.link.data.entity.Link;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
