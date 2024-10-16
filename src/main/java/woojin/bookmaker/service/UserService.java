package woojin.bookmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.repository.UserRepository;
import woojin.bookmaker.repository.Users;
import woojin.bookmaker.service.exception.CustomException;
import woojin.bookmaker.service.exception.UserErrorCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UsersDto createUser(String email, String password, String nickName) {
        if(userRepository.existsByUsersEmail(email)){
            throw new CustomException(UserErrorCode.EMAIL_DUPLICATE);
        }
        return  UsersDto.entityToDto(
                    userRepository.save(Users.of(email, password, nickName)));
    }

    public UsersDto updateUser(Integer userId, String email, String beforePassword, String changePassword, String userName) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new CustomException(UserErrorCode.NOT_EXISTS));

        if(!user.getEmail().equals(email) || !user.getPassword().equals(beforePassword)) {
            throw new CustomException(UserErrorCode.NOT_AUTHENTICATION);
        }
        user.update(email, changePassword, userName);
        return UsersDto.entityToDto(user);
    }
}
