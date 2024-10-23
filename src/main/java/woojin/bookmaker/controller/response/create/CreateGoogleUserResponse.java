package woojin.bookmaker.controller.response.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.handler.UserAuthDto;

@Getter
@Builder
@AllArgsConstructor
public class CreateGoogleUserResponse {
    private Integer id;
    private String email;
    private String userName;
    private String imageUrl;
    private String accessToken;
    private String created;
    private String updated;
    private Boolean deleted;
    public static CreateGoogleUserResponse dtoToResponse(UserAuthDto dto) {
        return CreateGoogleUserResponse.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .imageUrl(dto.getImageUrl())
                .accessToken(dto.getAccessToken())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}