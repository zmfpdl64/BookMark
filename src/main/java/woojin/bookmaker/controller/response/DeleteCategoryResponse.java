package woojin.bookmaker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import woojin.bookmaker.controller.request.DeleteCategoryRequest;
import woojin.bookmaker.service.CategoryDto;

@Getter
@Builder
@AllArgsConstructor
public class DeleteCategoryResponse {
    private Integer userId;
    private Integer categoryId;
    private String created;
    private String updated;
    private Boolean deleted;
    public static DeleteCategoryResponse dtoToResponse(CategoryDto dto) {
        return DeleteCategoryResponse.builder()
                .userId(dto.getUserId())
                .categoryId(dto.getId())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .deleted(dto.getDeleted())
                .build();
    }
}
