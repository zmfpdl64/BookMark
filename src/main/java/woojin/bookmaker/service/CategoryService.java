package woojin.bookmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.repository.*;
import woojin.bookmaker.service.exception.CategoryErrorCode;
import woojin.bookmaker.service.exception.CustomException;
import woojin.bookmaker.service.exception.UserErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<CategoryDto> getCategories(Integer userId) {
        //TODO: 유저 검증 또는 Filter에서 검증

        return categoryRepository.getCategoriesByUserId(userId).stream()
                .map(CategoryDto::entityToDto)
                .toList();
    }

    public CategoryDto createCategory(String title, Integer userId) {
//        TODO: 유저 검증은 추후에 활성화
//        유저 검증
//        if(!userRepository.existsById(userId)) {
//            throw new CustomException(UserErrorCode.NOT_EXISTS);
//        }
        // 카테고리 생성
        return CategoryDto.entityToDto(categoryRepository.save(Category.of(title, userId)));
    }


}
