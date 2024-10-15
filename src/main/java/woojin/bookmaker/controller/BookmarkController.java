package woojin.bookmaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.controller.response.CreateBookmarkResponse;
import woojin.bookmaker.controller.response.ReadBookmarkResponse;
import woojin.bookmaker.service.BookmarkDto;
import woojin.bookmaker.service.BookmarkService;

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
}
