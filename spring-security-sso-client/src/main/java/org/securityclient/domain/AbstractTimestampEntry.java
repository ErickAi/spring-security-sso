package org.securityclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTimestampEntry extends AbstractBaseEntity {

    @Column(name = "startpoint", columnDefinition = "timestamp default now()")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime startpoint = LocalDateTime.now();

    @Column(name = "endpoint")
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime endpoint = null;
}