package woojin.bookmaker.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.controller.request.UpdateBookmarkRequest;
import woojin.bookmaker.repository.*;
import woojin.bookmaker.service.exception.BookmarkErrorCode;
import woojin.bookmaker.service.exception.CategoryErrorCode;
import woojin.bookmaker.service.exception.CustomException;

import java.util.List;

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
        Bookmark bookmark = Bookmark.of(category.getId(), category.getUserId(), request.getTitle(), request.getLink());
        return BookmarkDto.entityToDto(bookmarkRepository.save(bookmark));
    }

    public List<BookmarkDto> getBookmarks(Integer userId, Integer categoryId) {
        return bookmarkRepository.findByUserIdAndCategoryId(userId, categoryId)
                .stream().map(BookmarkDto::entityToDto).toList();
    }

    @Transactional
    public BookmarkDto updateBookmark(@RequestBody UpdateBookmarkRequest request) {
        Bookmark bookmark = bookmarkRepository.findByUserIdAndId(request.getUserId(), request.getBookmarkId());
        if(bookmark == null) {
            throw new CustomException(BookmarkErrorCode.NOT_EXISTS);
        }
        bookmark.update(request.getTitle(), request.getLink());
        return BookmarkDto.entityToDto(bookmark);
    }
}
