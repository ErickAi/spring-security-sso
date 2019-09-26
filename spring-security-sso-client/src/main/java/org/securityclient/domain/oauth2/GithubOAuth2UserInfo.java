package org.securityclient.domain.oauth2;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Map;

import static org.securityclient.security.OAuth2UserInfoFactory.GITHUB;

@Entity
@NoArgsConstructor
public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        this.authProvider = GITHUB;
        this.providerId = ((Integer) attributes.get("id")).toString();
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
    }
}
