package woojin.bookmaker.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.type.descriptor.DateTimeUtils;
import woojin.bookmaker.repository.Users;
import woojin.bookmaker.utils.DateUtils;

@Getter
@Builder
@AllArgsConstructor
public class UsersDto {
    private Integer id;
    private String email;
    private String userName;
    private String created;
    private String updated;
    private Boolean deleted;
    public static UsersDto entityToDto(Users users) {
        return UsersDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .userName(users.getUserName())
                .created(DateUtils.dateTimeToString(users.getCreated()))
                .updated(DateUtils.dateTimeToString(users.getUpdated()))
                .deleted(users.getDeleted())
                .build();
    }
}
