package woojin.bookmaker.controller.request.update;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCategoryRequest {
    @NotNull(message = "값을 추가해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
    @NotNull(message = "카테고리 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 카테고리 아이디를 입력해주세요")
    private Integer categoryId;
    @Size(min = 0, max=255, message = "카테고리명 길이는 0 ~ 255byte로 작성해주세요")
    @NotBlank(message = "카테고리명을 입력해주세요")
    private String title;
}
