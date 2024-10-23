package woojin.bookmaker.controller.request.read;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleOAuthRequest {
    private String scope;
    private String accessType;
    private boolean includeGrantedScopes;
    private String responseType;
    private String state;
    private String redirectUri;
    private String clientId;
}
