package io.github.samirsales.facade;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.util.TextEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityDtoFacade {

    @Autowired
    @SuppressWarnings("unused")
    private UserDao userDao;

    public  UserEntity getEntityWithUpdatedPassword(UserEntity userEntity, String newPasswordNotEncrypted){
        String encryptedPassword = getMD5EncryptedText(newPasswordNotEncrypted);

        return new UserEntity(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLogin(),
                userEntity.getEmail(),
                userEntity.isActive(),
                encryptedPassword,
                userEntity.getGender(),
                userEntity.getRoleEntities(),
                userEntity.getImageEntity()
        );
    }

    public UserEntity getSavedEntitySetByDTO(Long idEntity, UserDTO userDTO){
        String password = getEncryptedPasswordByUserDTO(userDTO);
        final boolean isActiveUser = true;

        return new UserEntity(
                idEntity,
                userDTO.getName(),
                userDTO.getLogin(),
                userDTO.getEmail(),
                isActiveUser,
                password,
                userDTO.getGender(),
                userDTO.getRoleEntities(),
                null
        );
    }

    private String getEncryptedPasswordByUserDTO(UserDTO userDTO){
        boolean shouldSetPassword = userDTO.getPassword() != null && !userDTO.getPassword().isEmpty();

        if(shouldSetPassword){
            return getMD5EncryptedText(userDTO.getPassword());
        }

        UserEntity userEntity = userDao.getById(userDTO.getId());
        boolean isUserOnDatabase = userEntity != null;

        if(isUserOnDatabase){
            return userEntity.getPassword();
        }

        return "";
    }

    private String getMD5EncryptedText(String text){
        TextEncryption textEncryption = new TextEncryption();
        return textEncryption.getMD5(text);
    }
}
