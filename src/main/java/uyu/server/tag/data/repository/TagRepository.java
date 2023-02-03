package uyu.server.tag.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uyu.server.tag.data.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
