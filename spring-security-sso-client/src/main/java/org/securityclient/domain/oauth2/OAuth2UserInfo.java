package org.securityclient.domain.oauth2;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.securityclient.domain.AbstractRefEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@MappedSuperclass
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "oauth2_userinfo")
public abstract class OAuth2UserInfo extends AbstractRefEntity {

    @NotBlank
    @Size(max = 12)
    @Column(name = "user_id", nullable = false)
    @NotNull
    protected Integer userId;
    @NotBlank
    @Size(max = 12)
    @Column(name = "auth_provider", nullable = false)
    @NotNull
    protected String authProvider;
    @NotBlank
    @Size(max = 512)
    @Column(name = "provider_id", nullable = false)
    @NotNull
    protected String providerId;
    @NotBlank
    @Size(max = 512)
    @Column(name = "email", nullable = false)
    @NotNull
    protected String email;
}
