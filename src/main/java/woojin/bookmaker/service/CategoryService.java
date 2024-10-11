package woojin.bookmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.repository.Category;
import woojin.bookmaker.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories(Integer userId) {
        return categoryRepository.getCategoriesByUserId(userId).stream()
                .map(CategoryDto::entityToDto)
                .toList();
    }
}
