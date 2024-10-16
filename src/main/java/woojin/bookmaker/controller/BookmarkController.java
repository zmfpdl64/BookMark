package woojin.bookmaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.controller.request.DeleteBookmarkRequest;
import woojin.bookmaker.controller.request.UpdateBookmarkRequest;
import woojin.bookmaker.controller.response.CreateBookmarkResponse;
import woojin.bookmaker.controller.response.DeleteBookmarkResponse;
import woojin.bookmaker.controller.response.ReadBookmarkResponse;
import woojin.bookmaker.controller.response.UpdateBookmarkResponse;
import woojin.bookmaker.service.bookmark.BookmarkDto;
import woojin.bookmaker.service.bookmark.BookmarkService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path="/bookmark")
@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<?> getBookmarks(@RequestParam("userId") Integer userId,
                                          @RequestParam("categoryId") Integer categoryId) {
        //TODO: 인증인가 구현시 변경
        List<BookmarkDto> bookMarkDtoList = bookmarkService.getBookmarks(userId, categoryId);
        return ResponseEntity.ok(bookMarkDtoList.stream()
                .map(ReadBookmarkResponse::dtoToResponse).toList());
    }
    @PostMapping
    public ResponseEntity<?> createBookmark(@RequestBody CreateBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.createBookmark(request);
        return ResponseEntity.ok(CreateBookmarkResponse.dtoToResponse(dto));
    }

    @PutMapping
    public ResponseEntity<?> updateBookmark(@RequestBody UpdateBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.updateBookmark(request);
        return ResponseEntity.ok(UpdateBookmarkResponse.dtoToResponse(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@RequestBody DeleteBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.deleteBookmark(request.getUserId(), request.getBookmarkId());
        return ResponseEntity.ok(DeleteBookmarkResponse.dtoToResponse(dto));
    }

}
