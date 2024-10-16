package woojin.bookmaker.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.repository.Category;
import woojin.bookmaker.utils.DateUtils;

@Getter
@Builder
@AllArgsConstructor
public class CategoryDto {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String created;
    private final String updated;
    private final Boolean deleted;

    public static CategoryDto entityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .userId(category.getUserId())
                .title(category.getTitle())
                .created(DateUtils.dateTimeToString(category.getCreated()))
                .updated(DateUtils.dateTimeToString(category.getUpdated()))
                .deleted(category.getDeleted())
                .build();
    }
}