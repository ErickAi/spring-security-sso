package org.securityclient.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.securityclient.HasId;
import org.securityclient.domain.Role;
import org.securityclient.domain.User;
import org.securityclient.exception.ApplicationException;
import org.securityclient.exception.NotFoundException;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("WeakerAccess")
public class ValidationUtil {

    public static <T> T checkFoundOptional(Optional<T> optional, int id) {
        checkFoundById(optional.isPresent(), id);
        return optional.get();
    }

    public static void checkFoundById(boolean found, int id) {
        checkFound(found, "id=" + id);
    }

    public static <T> T checkFoundById(T object, int id) {
        return checkFound(object, "id=" + id);
    }

    public static <T> T checkFound(T object, String msg) {
        checkFound(object != null, msg);
        return object;
    }

    public static void checkFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkFoundByIdAndCoach(T object, int id, User coach) {
        return checkFound(object, "Not found entity with id=" + id + " owned by coach " + coach);
    }

    public static void checkUpdate(int count) {
        if (count != 1) {
            throw new ApplicationException("Update failed (count=" + count + ")");
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    // http://stackoverflow.com/a/32728226/548473
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static void checkRole(Set<Role> roles, Role role) {
        if (!roles.contains(role)) {
            throw new IllegalArgumentException("User has no role " + role);
        }
    }
}