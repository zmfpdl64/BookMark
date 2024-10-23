package woojin.bookmaker.controller.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "test@email.com 형식에 맞춰주세요")
    private String email;
    //    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message="8자 이상 문자, 숫자, 특수문자를 넣어서 조합해주세요")
    @NotBlank(message = "현재 비밀번호를 입력해주세요")
    private String beforePassword;
    @NotBlank(message = "바꿀 비밀번호를 입력해주세요")
    private String changePassword;
    @NotBlank(message = "사용하실 이름을 작성해주세요")
    private String userName;
    @NotNull(message = "값을 추가해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
}
