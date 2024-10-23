package woojin.bookmaker.controller.request.delete;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCategoryRequest {
    @NotNull(message = "카테고리 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 카테고리 아이디를 입력해주세요")
    private Integer categoryId;
    //TODO: 인증/인가 구현시 제거
    @NotNull(message = "유저 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
}
