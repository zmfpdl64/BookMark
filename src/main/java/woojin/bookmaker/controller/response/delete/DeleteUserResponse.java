package woojin.bookmaker.controller.response.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.handler.service.user.UsersDto;

@Getter
@Builder
@AllArgsConstructor
public class DeleteUserResponse {
    private Integer id;
    private String email;
    private String userName;
    private String created;
    private String updated;
    private Boolean deleted;
    public static DeleteUserResponse dtoToResponse(UsersDto dto) {
        return DeleteUserResponse.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}
