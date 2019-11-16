package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Gender gender;
    private String password;
    private ImageDTO imageProfile;
    private Set<RoleDTO> roles;

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
}
