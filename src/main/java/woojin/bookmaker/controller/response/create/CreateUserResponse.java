package woojin.bookmaker.controller.response.create;

import lombok.*;
import woojin.bookmaker.handler.service.user.UsersDto;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserResponse {
    private Integer id;
    private String email;
    private String userName;
    private String created;
    private String updated;
    private Boolean deleted;
    public static CreateUserResponse dtoToResponse(UsersDto dto) {
        return CreateUserResponse.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}
