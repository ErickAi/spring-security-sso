package org.securityclient.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.securityclient.HasName;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedEntity extends AbstractTimestampEntry implements HasName {

    @NotBlank
    @Size(max = 512)
    @Column(name = "name", nullable = false)
    @NotNull
    protected String name;

    protected AbstractNamedEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ',' + (getEndpoint() == null ? "active)" : "disabled " + getEndpoint() + ')');
    }
}