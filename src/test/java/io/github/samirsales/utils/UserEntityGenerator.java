package io.github.samirsales.utils;

import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;

import java.util.HashSet;
import java.util.Set;

public class UserEntityGenerator {

    @SuppressWarnings("ConstantConditions")
    public static UserEntity getUserEntityGeneratedById(long userId){
        Long id = userId;
        String name = "Name" + userId;
        String username = "username" + userId;
        String email = "user"+ userId +"@email.com";
        final boolean isActive = true;
        String password = "123456";
        Gender gender = Gender.MALE;
        Set<RoleEntity> roleEntities = new HashSet<>();
        final ImageEntity imageProfile = null;
        return new UserEntity(id, name, username, email,isActive, password, gender, roleEntities, imageProfile);
    }
}
