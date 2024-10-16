package woojin.bookmaker.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.CreateUserRequest;
import woojin.bookmaker.controller.request.DeleteUserRequest;
import woojin.bookmaker.controller.request.UpdateUserRequest;
import woojin.bookmaker.controller.response.CreateUserResponse;
import woojin.bookmaker.controller.response.DeleteUserResponse;
import woojin.bookmaker.controller.response.UpdateUserResponse;
import woojin.bookmaker.service.user.UserService;

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

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        UpdateUserResponse response =
                UpdateUserResponse.dtoToResponse(userService.updateUser(request.getUserId(), request.getEmail(), request.getBeforePassword(), request.getChangePassword(), request.getUserName()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request) {
        DeleteUserResponse response =
                DeleteUserResponse.dtoToResponse(userService.deleteUser(request.getUserId(), request.getEmail(), request.getPassword()));
        return ResponseEntity.ok(response);
    }
}
