package woojin.bookmaker.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCategoryRequest {
    @NotEmpty(message = "카테고리 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 카테고리 아이디를 입력해주세요")
    private Integer categoryId;
    //TODO: 인증/인가 구현시 제거
    @NotEmpty(message = "유저 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
}
