package org.securityclient.domain.oauth2;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Map;

import static org.securityclient.security.OAuth2UserInfoFactory.GOOGLE;

@Entity
@NoArgsConstructor
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.authProvider = GOOGLE;
        this.providerId = (String) attributes.get("sub");
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
    }
}
