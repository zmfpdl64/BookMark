package woojin.bookmaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

}
