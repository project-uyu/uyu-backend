package uyu.server.folder.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long id;
    private String title;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Folder parentFolder;

    @Builder
    public Folder(String title, Folder parentFolder) {
        this.title = title;
        this.parentFolder = parentFolder;
    }

    public void update(String title, Folder parentFolder){
        if(title != null) {
            this.title = title;
        }

        if(parentFolder != null){
            this.parentFolder = parentFolder;
        }
    }
}
