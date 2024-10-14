package woojin.bookmaker.service;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.repository.Bookmark;
import woojin.bookmaker.utils.DateUtils;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class BookmarkDto {
    private Integer id;
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String link;
    private String created;
    private String updated;
    private Boolean deleted;

    public static BookmarkDto entityToDto(Bookmark bookmark) {
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .categoryId(bookmark.getCategoryId())
                .userId(bookmark.getUserId())
                .title(bookmark.getTitle())
                .link(bookmark.getLink())
                .created(DateUtils.dateTimeToString(bookmark.getCreated()))
                .updated(DateUtils.dateTimeToString(bookmark.getUpdated()))
                .deleted(bookmark.getDeleted())
                .build();
    }

}