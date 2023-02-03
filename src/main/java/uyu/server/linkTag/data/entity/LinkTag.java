package uyu.server.linkTag.data.entity;

import jakarta.persistence.*;
import uyu.server.link.data.entity.Link;
import uyu.server.tag.data.entity.Tag;

public class LinkTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linktag_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id")
    private Link link;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
