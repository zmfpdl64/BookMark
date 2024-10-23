package woojin.bookmaker.handler.service.bookmark;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woojin.bookmaker.common.utils.DateUtils;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer categoryId;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false, length = 127)
    private String title;
    @Column(nullable = false, length = 512)
    private String link;

    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean deleted;

    public static Bookmark of(Integer categoryId, Integer userId, String title, String link) {
        return Bookmark.builder()
                .categoryId(categoryId)
                .userId(userId)
                .title(title)
                .link(link)
                .created(DateUtils.now())
                .updated(DateUtils.now())
                .deleted(false)
                .build();
    }

    public void update(String title, String link) {
        this.title = title;
        this.link = link;
        this.updated = DateUtils.now();
    }

    public void delete() {
        this.deleted = true;
        this.updated = DateUtils.now();
    }
}
