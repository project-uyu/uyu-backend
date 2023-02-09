package uyu.server.linkTag.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uyu.server.linkTag.data.entity.LinkTag;
import uyu.server.tag.data.entity.Tag;

import java.util.List;

public interface LinkTagRepository extends JpaRepository<LinkTag,Long> {
    @Query("select lt.tag from LinkTag lt join fetch lt.tag where lt.link.id = :linkId")
    List<Tag> findByIdUsingFetchJoinTag(Long linkId);
}
