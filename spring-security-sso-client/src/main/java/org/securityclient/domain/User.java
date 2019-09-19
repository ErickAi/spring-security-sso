package org.securityclient.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.securityclient.config.SecurityConfig;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractNamedEntity implements Serializable {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(max = 128)
    private String email;
    @Column(name = "password")
    @NotEmpty
    @Size(max = 256)
    private String password;
    @Column(name = "surname", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(max = 128)
    private String surname;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(User user) {
        this(user.id, user.email, user.name, user.surname, user.password, user.roles);
    }

    public User(Integer id, String email, String name, String surname, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = StringUtils.isEmpty(email) ? null : email.toLowerCase();
    }

    //    https://stackoverflow.com/questions/30260582/password-encoding-with-spring-data-rest
    public void setPassword(String rawPassword) {
        this.password = StringUtils.isEmpty(rawPassword) ? null : SecurityConfig.DELEGATING_PASSWORD_ENCODER.encode(rawPassword);
    }

    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", id=" + id +
            '}';
    }
}