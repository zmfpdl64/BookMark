package woojin.bookmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.repository.*;
import woojin.bookmaker.service.exception.CategoryErrorCode;
import woojin.bookmaker.service.exception.CustomException;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkDto createBookmark(CreateBookmarkRequest request) {

//        TODO: 유저 검증은 추후에 활성화
//        유저 검증
//        if(!userRepository.existsById(userId)) {
//            throw new CustomException(UserErrorCode.NOT_EXISTS);
//        }
        //카테고리 찾기
        Category category = categoryRepository.getCategoryById(request.getCategoryId());
        if(category==null) {
            throw new CustomException(CategoryErrorCode.NOT_EXISTS);
        }
        //북마크 생성
        Bookmark bookmark = Bookmark.of(category.getId(), category.getUserId(), request.getTitle(), request.getTitle());
        return BookmarkDto.entityToDto(bookmarkRepository.save(bookmark));
    }
}
