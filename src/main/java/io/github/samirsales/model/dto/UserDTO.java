package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    private String name;
    private String login;
    private String email;
    private Gender gender;
    private String password;

    private ImageDTO imageDTO;

    private Set<RoleEntity> roleEntities;

    public UserDTO(UserEntity userEntity){
        id = userEntity.getId();
        name = userEntity.getName();
        login = userEntity.getLogin();
        email = userEntity.getEmail();
        gender = userEntity.getGender();
        password = "";

        if(userEntity.getImageEntity() != null){
            imageDTO = new ImageDTO(userEntity.getImageEntity(), "");
        }

        roleEntities = new HashSet<>();
        for(RoleEntity roleEntity : userEntity.getRoleEntities()){
            roleEntities.add(new RoleEntity(roleEntity.getId(), roleEntity.getRole()));
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
    public String getLogin() {
        return login;
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
    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    @SuppressWarnings("unused")
    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }
}
