package woojin.bookmaker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.create.CreateBookmarkRequest;
import woojin.bookmaker.controller.request.delete.DeleteBookmarkRequest;
import woojin.bookmaker.controller.request.update.UpdateBookmarkRequest;
import woojin.bookmaker.controller.response.create.CreateBookmarkResponse;
import woojin.bookmaker.controller.response.delete.DeleteBookmarkResponse;
import woojin.bookmaker.controller.response.read.ReadBookmarkResponse;
import woojin.bookmaker.controller.response.update.UpdateBookmarkResponse;
import woojin.bookmaker.handler.service.bookmark.BookmarkDto;
import woojin.bookmaker.handler.service.bookmark.BookmarkService;

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
    public ResponseEntity<?> createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.createBookmark(request);
        return ResponseEntity.ok(CreateBookmarkResponse.dtoToResponse(dto));
    }

    @PutMapping
    public ResponseEntity<?> updateBookmark(@RequestBody @Valid UpdateBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.updateBookmark(request);
        return ResponseEntity.ok(UpdateBookmarkResponse.dtoToResponse(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@RequestBody @Valid DeleteBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.deleteBookmark(request.getUserId(), request.getBookmarkId());
        return ResponseEntity.ok(DeleteBookmarkResponse.dtoToResponse(dto));
    }

}
