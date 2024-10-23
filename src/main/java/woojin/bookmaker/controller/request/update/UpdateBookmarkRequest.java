package woojin.bookmaker.controller.request.update;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBookmarkRequest {
    @NotNull(message = "유저 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 유저 아이디를 입력해주세요")
    private Integer userId;
    @NotNull(message = "북마크 아이디를 입력해주세요")
    @Min(value = 1 ,message = "1이상의 북마크 아이디를 입력해주세요")
    private Integer bookmarkId;
    @Size(min = 0, max=255, message = "북마크 길이는 0 ~ 255byte로 작성해주세요")
    @NotBlank(message = "북마크 명을 입력해주세요")
    private String title;
    @Size(min = 0, max=512, message = "링크의 길이는 0 ~ 512byte로 작성해주세요")
    @NotBlank(message = "링크를 입력해주세요")
    private String link;

}
