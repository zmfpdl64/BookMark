package woojin.bookmaker.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCategoryRequest {
    @Size(min = 0, max=255, message = "카테고리명 길이는 0 ~ 255byte로 작성해주세요")
    @NotBlank(message = "카테고리명을 입력해주세요")
    private String title;
    //TODO: 인증/인가 구현시 제거
    @NotEmpty(message = "값을 추가해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
}
