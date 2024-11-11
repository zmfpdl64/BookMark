package woojin.bookmaker.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import woojin.bookmaker.adaptor.service.CustomException;
import woojin.bookmaker.adaptor.service.oauth.GoogleTokenInfo;
import woojin.bookmaker.adaptor.service.user.UsersDto;
import woojin.bookmaker.adaptor.service.oauth.GoogleOAuthService;
import woojin.bookmaker.adaptor.service.oauth.GoogleUserInfo;
import woojin.bookmaker.adaptor.service.user.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthAdaptor {
    private final UserService userService;
    private final GoogleOAuthService googleOAuthService;

    public UserAuthDto oauthSignUp(String code) {
        //Access Token 가져오기
        GoogleTokenInfo tokenInfo = googleOAuthService.getToken(code);
        log.info("token 정보: {}", tokenInfo);
        
        //유저 정보 가져오기
        GoogleUserInfo profile = googleOAuthService.getProfile(tokenInfo.getAccessToken());
        log.info("profile 정보: {}", profile);
        UsersDto userDto = null;
        try{
            userDto = userService.getUser(profile.getEmail());
            log.info("로그인 유저: {}", userDto);
        }catch(CustomException e) {
            userDto = userService.createGoogleUser(profile.getEmail(), "", profile.getName(), profile.getPicture());
            log.info("회원가입 기존의 없는 유저: {}", userDto);
        }
        return UserAuthDto.combine(userDto, tokenInfo.getAccessToken());
    }
}
