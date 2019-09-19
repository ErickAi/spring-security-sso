package org.securityclient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

public interface HasId extends Comparable<HasId> {
    Integer getId();

    void setId(Integer id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    default int id() {
        Assert.notNull(getId(), "Entity " + this + " must has id");
        return getId();
    }

    @Override
    default int compareTo(HasId o) {
        return id() - o.id();
    }
}
