package woojin.bookmaker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.create.CreateCategoryRequest;
import woojin.bookmaker.controller.request.delete.DeleteCategoryRequest;
import woojin.bookmaker.controller.request.update.UpdateCategoryRequest;
import woojin.bookmaker.controller.response.create.CreateCategoryResponse;
import woojin.bookmaker.controller.response.delete.DeleteCategoryResponse;
import woojin.bookmaker.controller.response.read.ReadCategoryResponse;
import woojin.bookmaker.controller.response.update.UpdateCategoryResponse;
import woojin.bookmaker.handler.service.category.CategoryDto;
import woojin.bookmaker.handler.service.category.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCategories(@PathVariable("userId") Integer userId) {
        List<ReadCategoryResponse> categoryList = categoryService.getCategories(userId)
                .stream().map(ReadCategoryResponse::dtoToResponse).toList();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        //TODO: 인증/인가 적용 시 userId 리펙토링
        CategoryDto dto = categoryService.createCategory(request.getTitle(), request.getUserId());
        return ResponseEntity.ok(CreateCategoryResponse.dtoToResponse(dto));
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody @Valid UpdateCategoryRequest request) {
        //TODO: 인증/인가 적용 시 userId 리펙토링
        CategoryDto dto = categoryService.updateCategory(request.getCategoryId(), request.getUserId(), request.getTitle());
        return ResponseEntity.ok(UpdateCategoryResponse.dtoToResponse(dto));
    }

    @DeleteMapping
    public ResponseEntity<DeleteCategoryResponse> deleteCategory(@RequestBody DeleteCategoryRequest request) {
        DeleteCategoryResponse response = DeleteCategoryResponse.dtoToResponse(categoryService.deleteCategory(request.getUserId(), request.getCategoryId()));
        return ResponseEntity.ok(response);
    }

}
