package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.service.CategoryDto;

@Builder
@Getter
@AllArgsConstructor
public class ReadCategoryResponse {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String created;
    private final String updated;
    private final Boolean deleted;

    public static ReadCategoryResponse dtoToResponse(CategoryDto dto) {
        return ReadCategoryResponse.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }

}