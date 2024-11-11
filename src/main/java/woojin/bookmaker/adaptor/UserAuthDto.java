package woojin.bookmaker.adaptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.adaptor.service.user.UsersDto;

@Getter
@Builder
@AllArgsConstructor
public class UserAuthDto {
    private Integer id;
    private String email;
    private String userName;
    private String imageUrl;
    private String accessToken;
    private String created;
    private String updated;
    private Boolean deleted;
    public static UserAuthDto combine(UsersDto usersDto, String accessToken) {
        return UserAuthDto.builder()
                .id(usersDto.getId())
                .email(usersDto.getEmail())
                .userName(usersDto.getUserName())
                .imageUrl(usersDto.getImageUrl())
                .accessToken(accessToken)
                .created(usersDto.getCreated())
                .updated(usersDto.getUpdated())
                .deleted(usersDto.getDeleted())
                .build();
    }
}
