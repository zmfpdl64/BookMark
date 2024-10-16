package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.service.category.CategoryDto;

@Getter
@Builder
@AllArgsConstructor
public class UpdateCategoryResponse {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String created;
    private final String updated;
    private final Boolean deleted;

    public static UpdateCategoryResponse dtoToResponse(CategoryDto dto) {
        return UpdateCategoryResponse.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }

}
