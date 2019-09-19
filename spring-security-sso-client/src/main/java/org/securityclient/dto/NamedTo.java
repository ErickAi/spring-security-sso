package org.securityclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.securityclient.HasName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NamedTo extends BaseTo implements HasName {
    @NotNull
    @NotBlank
    protected String name;

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
