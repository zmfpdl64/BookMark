package woojin.bookmaker.controller.response.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.handler.service.bookmark.BookmarkDto;

@Getter
@Builder
@AllArgsConstructor
public class DeleteBookmarkResponse {
    private Integer id;
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String link;
    private String created;
    private String updated;
    private Boolean deleted;
    public static DeleteBookmarkResponse dtoToResponse(BookmarkDto dto) {
        return DeleteBookmarkResponse.builder()
                .id(dto.getId())
                .categoryId(dto.getCategoryId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .link(dto.getLink())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}
