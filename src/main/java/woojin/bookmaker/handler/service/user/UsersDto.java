package woojin.bookmaker.handler.service.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.common.utils.DateUtils;

@Getter
@Builder
@AllArgsConstructor
public class UsersDto {
    private Integer id;
    private String email;
    private String userName;
    private String imageUrl;
    private String created;
    private String updated;
    private Boolean deleted;
    public static UsersDto entityToDto(Users users) {
        return UsersDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .userName(users.getUserName())
                .imageUrl(users.getImageUrl())
                .created(DateUtils.dateTimeToString(users.getCreated()))
                .updated(DateUtils.dateTimeToString(users.getUpdated()))
                .deleted(users.getDeleted())
                .build();
    }
}
