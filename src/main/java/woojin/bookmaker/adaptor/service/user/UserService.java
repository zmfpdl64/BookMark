package woojin.bookmaker.adaptor.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woojin.bookmaker.adaptor.service.CustomException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public synchronized UsersDto createUser(String email, String password, String nickName) {
        if(userRepository.existsByUsersEmail(email)){
            throw new CustomException(UserErrorCode.EMAIL_DUPLICATE);
        }
        return  UsersDto.entityToDto(
                    userRepository.save(Users.of(email, password, nickName)));
    }

    public synchronized UsersDto createGoogleUser(String email, String password, String nickName, String imageUrl) {
        if(userRepository.existsByUsersEmail(email)){
            throw new CustomException(UserErrorCode.EMAIL_DUPLICATE);
        }
        return  UsersDto.entityToDto(
                userRepository.save(Users.socialOf(email, password, nickName, imageUrl)));
    }

    @Transactional
    public UsersDto updateUser(Integer userId, String email, String beforePassword, String changePassword, String userName) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new CustomException(UserErrorCode.NOT_EXISTS));

        if(!user.getEmail().equals(email) || !user.getPassword().equals(beforePassword)) {
            throw new CustomException(UserErrorCode.NOT_AUTHENTICATION);
        }
        user.update(email, changePassword, userName);
        return UsersDto.entityToDto(user);
    }

    public UsersDto deleteUser(Integer userId, String email, String password) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new CustomException(UserErrorCode.NOT_EXISTS));
        if(!user.getEmail().equals(email) || user.getPassword().equals(password)) {
            throw new CustomException(UserErrorCode.NOT_AUTHENTICATION);
        }
        user.delete();
        return UsersDto.entityToDto(user);
    }

    public UsersDto getUser(String email) {
        Users users = userRepository.findByEmail(email);
        //TODO: 토큰 검증 추가
        if(users == null) {
            throw new CustomException(UserErrorCode.NOT_EXISTS);
        }
        return UsersDto.entityToDto(users);
    }
}
