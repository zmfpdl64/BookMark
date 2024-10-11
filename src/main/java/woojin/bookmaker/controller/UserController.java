package woojin.bookmaker.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateUserRequest;
import woojin.bookmaker.controller.response.CreateUserResponse;
import woojin.bookmaker.service.UserService;
import woojin.bookmaker.service.exception.CustomException;
import woojin.bookmaker.service.exception.UserErrorCode;

@RequiredArgsConstructor
@RequestMapping(path = "/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response =
                CreateUserResponse.dtoToResponse(userService.createUser(request.getEmail(), request.getPassword(), request.getUserName()));

        return ResponseEntity.ok(response);
    }

}
