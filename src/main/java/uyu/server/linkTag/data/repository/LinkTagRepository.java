package uyu.server.linkTag.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uyu.server.linkTag.data.entity.LinkTag;
import uyu.server.tag.data.entity.Tag;

import java.util.List;

public interface LinkTagRepository extends JpaRepository<LinkTag,Long> {
    @Query("select lt.tag from LinkTag lt join fetch lt.tag where lt.link.id = :linkId")
    List<Tag> findTagsByLinkIdUsingFetchJoinTag(Long linkId);

    @Query("select lt.tag.id from LinkTag lt where lt.link.id = :linkId")
    List<Long> findTagIdsByLinkId(Long linkId);

    @Query("select lt from LinkTag lt where lt.tag.id = :tagId and lt.link.id = :linkId")
    LinkTag findLinkTagByLinkIdAndTagId(Long linkId, Long tagId);

    @Query("select lt from LinkTag lt where lt.link.id = :linkId")
    List<LinkTag> findLinkTagsByLinkId(Long linkId);

}
