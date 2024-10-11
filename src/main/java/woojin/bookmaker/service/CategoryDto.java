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
    private final String name;
    private final String created;
    private final String updated;
    private final Boolean isDeleted;

    public static CategoryDto entityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .created(DateUtils.dateTimeToString(category.getCreated()))
                .updated(DateUtils.dateTimeToString(category.getUpdated()))
                .isDeleted(category.getDeleted())
                .build();
    }
}