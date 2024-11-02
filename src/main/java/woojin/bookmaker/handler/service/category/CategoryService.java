package woojin.bookmaker.handler.service.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import woojin.bookmaker.handler.service.bookmark.BookmarkRepository;
import woojin.bookmaker.handler.service.CustomException;
import woojin.bookmaker.handler.service.user.UserRepository;

import java.util.List;

@Slf4j
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


    @Transactional
    public CategoryDto updateCategory(Integer categoryId, Integer userId, String title) {
        log.info("카테고리 업데이트: categoryId:{} , userId:{} title:{}", categoryId, userId, title);
        Category category = categoryRepository.getCategoryById(categoryId);
        if(category==null){
            throw new CustomException(CategoryErrorCode.NOT_EXISTS);
        }
        if(!category.getUserId().equals(userId)){
            throw new CustomException(CategoryErrorCode.NOT_AUTHORIZATION);
        }
        category.update(title);
        return CategoryDto.entityToDto(category);
    }

    @Transactional
    public CategoryDto deleteCategory(Integer userId, Integer categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        if(category==null) {
            throw new CustomException(CategoryErrorCode.NOT_EXISTS);
        }
        if(category.getUserId() != userId) {
            throw new CustomException(CategoryErrorCode.NOT_AUTHORIZATION);
        }
        category.delete();
        return CategoryDto.entityToDto(category);
    }
}
