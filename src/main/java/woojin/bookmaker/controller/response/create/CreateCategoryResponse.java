package woojin.bookmaker.controller.response.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.adaptor.service.category.CategoryDto;

@Builder
@Getter
@AllArgsConstructor
public class CreateCategoryResponse {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String link;
    private final String created;
    private final String updated;
    private final Boolean deleted;

    public static CreateCategoryResponse dtoToResponse(CategoryDto dto) {
        return CreateCategoryResponse.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }

}
