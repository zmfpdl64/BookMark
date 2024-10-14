package woojin.bookmaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateBookmarkRequest;
import woojin.bookmaker.controller.response.CreateBookmarkResponse;
import woojin.bookmaker.service.BookmarkDto;
import woojin.bookmaker.service.BookmarkService;

@RequiredArgsConstructor
@RequestMapping(path="/bookmark")
@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<?> createBookmark(@RequestBody CreateBookmarkRequest request) {
        BookmarkDto dto = bookmarkService.createBookmark(request);
        return ResponseEntity.ok(CreateBookmarkResponse.dtoToResponse(dto));
    }
}
