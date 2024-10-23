package woojin.bookmaker.controller.request.delete;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteBookmarkRequest {
    private Integer userId;
    private Integer bookmarkId;
}
