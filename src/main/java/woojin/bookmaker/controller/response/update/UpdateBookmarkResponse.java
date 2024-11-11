package woojin.bookmaker.controller.response.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.adaptor.service.bookmark.BookmarkDto;

@Getter
@AllArgsConstructor
@Builder
public class UpdateBookmarkResponse {
    private Integer id;
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String link;
    private String created;
    private String updated;
    private Boolean deleted;
    public static UpdateBookmarkResponse dtoToResponse(BookmarkDto dto) {
        return UpdateBookmarkResponse.builder()
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
