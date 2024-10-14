package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.service.CategoryDto;

@Builder
@Getter
@AllArgsConstructor
public class CreateCategoryResponse {
    private final Integer id;
    private final String title;
    private final String link;
    private final String created;
    private final String updated;
    private final Boolean deleted;

    public static CreateCategoryResponse dtoToResponse(CategoryDto dto) {
        return CreateCategoryResponse.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }

}
