package woojin.bookmaker.controller.request.create;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBookmarkRequest {
    @Size(min = 0, max=255, message = "북마크 길이는 0 ~ 255byte로 작성해주세요")
    @NotBlank(message = "북마크 명을 입력해주세요")
    private String title;
    @Size(min = 0, max=512, message = "링크의 길이는 0 ~ 512byte로 작성해주세요")
    @NotBlank(message = "링크를 입력해주세요")
    private String link;
    @NotNull(message = "카테고리 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 카테고리 아이디를 입력해주세요")
    private Integer categoryId;
    @NotNull(message = "유저 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
}
