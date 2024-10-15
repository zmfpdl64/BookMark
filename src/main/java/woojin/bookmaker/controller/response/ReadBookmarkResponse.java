package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.service.BookmarkDto;

@Getter

@Builder
@AllArgsConstructor
public class ReadBookmarkResponse {
    private Integer id;
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String link;
    private String created;
    private String updated;
    private Boolean deleted;
    public static ReadBookmarkResponse dtoToResponse(BookmarkDto dto) {
        return ReadBookmarkResponse.builder()
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
