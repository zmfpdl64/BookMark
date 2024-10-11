package woojin.bookmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.repository.UserRepository;
import woojin.bookmaker.repository.Users;
import woojin.bookmaker.service.exception.CustomException;
import woojin.bookmaker.service.exception.UserErrorCode;

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
}
