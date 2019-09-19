package org.securityclient.domain;

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
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractRefEntity extends AbstractBaseEntity implements HasName {

    @NotBlank
    @Size(max = 512)
    @Column(name = "name", nullable = false)
    @NotNull
    protected String name;

    protected AbstractRefEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
