package woojin.bookmaker.controller.response.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.adaptor.service.bookmark.BookmarkDto;

@Builder
@AllArgsConstructor
@Getter
public class CreateBookmarkResponse {
    private Integer id;
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String link;
    private String created;
    private String updated;
    private Boolean deleted;

    public static CreateBookmarkResponse dtoToResponse(BookmarkDto dto) {
        return CreateBookmarkResponse.builder()
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
