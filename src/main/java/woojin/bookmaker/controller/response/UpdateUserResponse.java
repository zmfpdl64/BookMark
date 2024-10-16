package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.service.user.UsersDto;


@Getter
@Builder
@AllArgsConstructor
public class UpdateUserResponse {
    private Integer id;
    private String email;
    private String userName;
    private String created;
    private String updated;
    private Boolean deleted;
    public static UpdateUserResponse dtoToResponse(UsersDto dto) {
        return UpdateUserResponse.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}
