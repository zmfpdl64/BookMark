package woojin.bookmaker.handler.service.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woojin.bookmaker.common.utils.PrintUtils;

@Getter
@NoArgsConstructor
public class GoogleTokenInfo {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("id_token")
    private String idToken;

    @Override
    public String toString() {
        return PrintUtils.print(this);
    }
}
