package woojin.bookmaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.controller.request.CreateCategoryRequest;
import woojin.bookmaker.controller.response.CreateBookmarkResponse;
import woojin.bookmaker.controller.response.CreateCategoryResponse;
import woojin.bookmaker.service.BookmarkDto;
import woojin.bookmaker.service.CategoryDto;
import woojin.bookmaker.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCategories(@PathVariable("userId") Integer userId) {
        List<CategoryDto> categoryList = categoryService.getCategories(userId);
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request) {
        //TODO: 인증/인가 적용 시 userId 리펙토링
        CategoryDto dto = categoryService.createCategory(request.getTitle(), request.getUserId());
        return ResponseEntity.ok(CreateCategoryResponse.dtoToResponse(dto));
    }

}
