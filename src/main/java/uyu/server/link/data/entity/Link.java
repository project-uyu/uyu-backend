package uyu.server.link.data.entity;

import jakarta.persistence.*;
import lombok.*;
import uyu.server.folder.data.entity.Folder;
import uyu.server.link.web.dto.LinkRequestDto;
import uyu.server.util.BaseTimeEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "link")
public class Link extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;
    private String url;
    private String content;
    private Long hit;
    private boolean isDeleted;
    private LocalDate deletedDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Builder // 빌더 형태로 만들어줌
    public Link(String url, String content,Folder folder) {
        this.url = url;
        this.content = content;
        this.hit = 0L;
        this.folder = folder;
        this.isDeleted = false;
    }

    public Link update(LinkRequestDto linkDto) {
        this.content = linkDto.getContent();
        this.url = linkDto.getUrl();
        return this;
    }
}
