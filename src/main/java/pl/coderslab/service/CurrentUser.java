package pl.coderslab.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.entity.Role;

public class CurrentUser extends User {
    private Long id;
    private Role role;
    public CurrentUser(String username, String password, Long id, Role role,
                       Collection<?extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.role = role;

    }
    public Role getRole() {return role;}
    public Long getId() {return id;}
}