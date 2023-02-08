package uyu.server.tag.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;
    private String name;

    @Builder
    public Tag(String name) {
        this.name = name;
    }

    public void setName(String name) { this.name = name; }
}
