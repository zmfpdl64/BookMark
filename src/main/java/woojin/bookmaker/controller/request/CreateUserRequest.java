package woojin.bookmaker.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "test@email.com 형식에 맞춰주세요")
    private String email;
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message="8자 이상 문자, 숫자, 특수문자를 넣어서 조합해주세요")
    @NotBlank(message = "공백이나 빈칸")
    private String password;
    @NotBlank(message = "사용하실 이름을 작성해주세요")
    private String userName;
}
