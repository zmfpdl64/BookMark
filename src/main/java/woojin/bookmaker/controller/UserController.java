package woojin.bookmaker.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woojin.bookmaker.controller.request.create.CreateUserRequest;
import woojin.bookmaker.controller.request.delete.DeleteUserRequest;
import woojin.bookmaker.controller.request.update.UpdateUserRequest;
import woojin.bookmaker.controller.response.create.CreateGoogleUserResponse;
import woojin.bookmaker.controller.response.create.CreateUserResponse;
import woojin.bookmaker.controller.response.delete.DeleteUserResponse;
import woojin.bookmaker.controller.response.update.UpdateUserResponse;
import woojin.bookmaker.handler.UserAuthHandler;
import woojin.bookmaker.handler.service.user.UserService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@RestController
public class UserController {

    private final UserService userService;
    private final UserAuthHandler authHandler;

    @GetMapping(path =  "/google/callback")
    public ResponseEntity<?> googleSignUp(@RequestParam("code") String code) {
        log.info("로그인 : {}", code);
        CreateGoogleUserResponse response = CreateGoogleUserResponse.dtoToResponse(authHandler.oauthSignUp(code));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response =
                CreateUserResponse.dtoToResponse(userService.createUser(request.getEmail(), request.getPassword(), request.getUserName()));

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        UpdateUserResponse response =
                UpdateUserResponse.dtoToResponse(userService.updateUser(request.getUserId(), request.getEmail(), request.getBeforePassword(), request.getChangePassword(), request.getUserName()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody @Valid DeleteUserRequest request) {
        DeleteUserResponse response =
                DeleteUserResponse.dtoToResponse(userService.deleteUser(request.getUserId(), request.getEmail(), request.getPassword()));
        return ResponseEntity.ok(response);
    }

}
