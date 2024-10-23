package woojin.bookmaker.handler.service.user;

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
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 127)
    private String userName;

    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean deleted;

    public static Users of(String email, String password, String userName) {
        return Users.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .created(DateUtils.now())
                .updated(DateUtils.now())
                .deleted(false)
                .build();
    }
    public static Users socialOf(String email, String password, String userName, String imageUrl) {
        return Users.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .imageUrl(imageUrl)
                .created(DateUtils.now())
                .updated(DateUtils.now())
                .deleted(false)
                .build();
    }

    public void update(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.updated = DateUtils.now();
    }

    public void delete() {
        this.deleted = true;
        this.updated = DateUtils.now();
    }
}
