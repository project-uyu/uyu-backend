package uyu.server.tag.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.tag.repository.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
