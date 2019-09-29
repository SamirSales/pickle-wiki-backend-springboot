package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String email;
    private boolean active;
    private String pictureFileName;
    private String gender;
    private List<RoleEntity> roleEntities;

    public UserDTO(UserEntity userEntity){
        id = userEntity.getId();
        name = userEntity.getName();
        login = userEntity.getLogin();
        email = userEntity.getEmail();
        active = userEntity.isActive();
        gender = userEntity.getGender().getValue();
        pictureFileName = userEntity.getImageEntity().getFilename();

        roleEntities = new ArrayList<>();
        for(RoleEntity roleEntity : userEntity.getRoleEntities()){
            roleEntities.add(new RoleEntity(roleEntity.getId(), roleEntity.getRole()));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }
}
