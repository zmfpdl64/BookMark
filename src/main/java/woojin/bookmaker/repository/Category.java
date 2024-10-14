package woojin.bookmaker.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woojin.bookmaker.utils.DateUtils;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
//    @Column(nullable = false)
//    private String link;
    @Column(nullable = false)
    private Integer userId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean deleted;

    public static Category of(String title, Integer userId) {
        return Category.builder()
                .title(title)
                .userId(userId)
                .created(DateUtils.now())
                .updated(DateUtils.now())
                .deleted(false)
                .build();
    }
}
