package woojin.bookmaker.controller.request.delete;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteUserRequest {
    @NotNull(message = "값을 추가해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
    @NotBlank(message = "test@email.com 형식에 맞춰주세요")
    private String email;
    @NotBlank(message = "현재 비밀번호를 입력해주세요")
    private String password;
}
