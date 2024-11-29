package woojin.bookmaker.adaptor.service.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleOAuthService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String secretKey;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;
    private String grantType = "authorization_code";

    private final String GOOGLE_URL_TOKEN = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_URL_USER_INFO = "https://www.googleapis.com/oauth2/v3/userinfo";

    private final RestTemplate restTemplate;

    public GoogleTokenInfo getToken(String code) {

        log.info("redirectUri : {}", redirectUri);

        // 요청 본문에 필요한 데이터 설정
        Map<String, String> data = new HashMap<>();
        data.put("code", code);
        data.put("client_id", clientId);
        data.put("client_secret", secretKey);
        data.put("redirect_uri", redirectUri);
        data.put("grant_type", grantType);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // 요청 엔티티 생성
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(data, headers);

        // 액세스 토큰 요청
        ResponseEntity<GoogleTokenInfo> response = restTemplate.exchange(GOOGLE_URL_TOKEN, HttpMethod.POST, entity, GoogleTokenInfo.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // 액세스 토큰 반환
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get access token: " + response.getStatusCode() + " - " + response.getBody());
        }


    }
    public GoogleUserInfo getProfile(String accessToken) {
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);


        // 요청 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 사용자 프로필 정보 요청
        ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(GOOGLE_URL_USER_INFO, HttpMethod.GET, entity, GoogleUserInfo.class);

        return response.getBody(); // 프로필 정보 반환
    }
}
