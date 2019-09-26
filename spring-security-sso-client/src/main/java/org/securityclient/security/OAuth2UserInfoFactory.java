package org.securityclient.security;


import org.securityclient.domain.oauth2.GithubOAuth2UserInfo;
import org.securityclient.domain.oauth2.GoogleOAuth2UserInfo;
import org.securityclient.domain.oauth2.OAuth2UserInfo;
import org.securityclient.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static final String GOOGLE = "google";
    public static final String GITHUB = "github";

    static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(GOOGLE)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(GITHUB)) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
