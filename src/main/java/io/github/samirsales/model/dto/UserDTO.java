package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    private String name;
    private String username;
    private String email;
    private Gender gender;
    private String password;

    private ImageDTO imageProfile;

    private Set<RoleDTO> roles;

    @SuppressWarnings("unused")
    public UserDTO() {
    }

    public UserDTO(UserEntity userEntity){
        id = userEntity.getId();
        name = userEntity.getName();
        username = userEntity.getUsername();
        email = userEntity.getEmail();
        gender = userEntity.getGender();
        password = "";

        if(userEntity.getImageProfile() != null){
            imageProfile = new ImageDTO(userEntity.getImageProfile(), "");
        }

        roles = new HashSet<>();
        for(RoleEntity roleEntity : userEntity.getRoleEntities()){
            roles.add(new RoleDTO(roleEntity));
        }
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused")
    public String getEmail() {
        return email;
    }

    @SuppressWarnings("unused")
    public Gender getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public ImageDTO getImageProfile() {
        return imageProfile;
    }

    @SuppressWarnings("unused")
    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
