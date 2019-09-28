package io.github.samirsales.security;

import io.github.samirsales.model.enums.PermissionType;
import io.github.samirsales.model.entity.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(){

    }

    public UserSS(Long id, String login, String password, Permission permission){
        super();
        ArrayList<SimpleGrantedAuthority> userTypes = new ArrayList<>();
        userTypes.add(new SimpleGrantedAuthority(permission.getPermissionType().getValue()));
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = userTypes;
    }

    public UserSS(Long id, String login, String password, List<Permission> permission){
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = permission.stream().map(pt ->
                new SimpleGrantedAuthority(pt.getPermissionType().getValue())).collect(Collectors.toList());
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

    public boolean hashRole(PermissionType permissionType) {
        return getAuthorities().contains(new SimpleGrantedAuthority(permissionType.getValue()));
    }
}
