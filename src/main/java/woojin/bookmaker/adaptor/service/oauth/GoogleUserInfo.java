package woojin.bookmaker.adaptor.service.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woojin.bookmaker.common.utils.PrintUtils;

@Getter
@NoArgsConstructor
public class GoogleUserInfo {

    @JsonProperty("sub")
    private String sub;              // 사용자 ID (sub)

    @JsonProperty("name")
    private String name;             // 사용자 이름 (name)

    @JsonProperty("given_name")
    private String givenName;        // 이름 (given_name)

    @JsonProperty("family_name")
    private String familyName;       // 성 (family_name)

    @JsonProperty("picture")
    private String picture;          // 프로필 사진 URL (picture)

    @JsonProperty("email")
    private String email;            // 이메일 주소 (email)

    @JsonProperty("email_verified")
    private boolean emailVerified;    // 이메일 인증 여부 (email_verified)

    @Override
    public String toString() {
        return PrintUtils.print(this);
    }
}
