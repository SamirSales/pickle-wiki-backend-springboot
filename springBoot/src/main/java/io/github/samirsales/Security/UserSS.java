package io.github.samirsales.Security;

import io.github.samirsales.Entity.Enum.UserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(){

    }

    public UserSS(Long id, String login, String password, UserPermission userPermission){
        super();
        ArrayList<SimpleGrantedAuthority> userTypes = new ArrayList<>();
        userTypes.add(new SimpleGrantedAuthority(userPermission.getValue()));
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = userTypes;
    }

    public UserSS(Long id, String login, String password, Set<UserPermission> userPermissions){
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = userPermissions.stream().map(ut ->
                new SimpleGrantedAuthority(ut.getValue())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
