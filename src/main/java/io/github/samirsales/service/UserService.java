package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.exception.AuthorizationException;
import io.github.samirsales.facade.UserEntityDtoFacade;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.security.UserSecurity;
import io.github.samirsales.util.ImageResizer;
import io.github.samirsales.util.TextEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    @SuppressWarnings("unused")
    private UserDao userDao;

    @Autowired
    @SuppressWarnings("unused")
    private UserEntityDtoFacade userEntityDtoFacade;

    @Value("${uploading.image.path.profiles}")
    @SuppressWarnings("unused")
    private String imagePath;

    public static UserSecurity authenticated(){
        try{
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public List<UserDTO> getAll(){
        Collection<UserEntity> userEntities = userDao.getAll();
        return userEntities.parallelStream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getById(Long id){
        return new UserDTO(userDao.getById(id));
    }

    public void create(UserDTO userDTO) {
        UserEntity userEntity = userEntityDtoFacade.getActiveEntityByDTO(userDTO);
        this.userDao.create(userEntity);
    }

    public void removeById(long id) {
        this.userDao.deleteById(id);
    }

    public void update(UserDTO userDTO){
        UserEntity userEntity = userEntityDtoFacade.getActiveEntitySetByDTO(userDTO.getId(), userDTO);
        this.userDao.update(userEntity);
    }

    public UserDTO getAuthenticatedUser() {
        UserSecurity userSecurity = getUserSecurity();
        return getById(userSecurity.getId());
    }

    public UserDTO getUpdatedAuthenticatedUserByDTO(UserDTO userDTO) {
        UserSecurity userSecurity = getUserSecurity();
        Long idAuthenticatedUser = userSecurity.getId();
        UserEntity userEntitySetByDTO = userEntityDtoFacade.getActiveEntitySetByDTO(idAuthenticatedUser, userDTO);
        userDao.update(userEntitySetByDTO);

        UserEntity updatedUserEntity = this.userDao.getById(userSecurity.getId());
        return new UserDTO(updatedUserEntity);
    }

    public void setUserPassword(String currentPassword, String newPassword){
        UserSecurity userSecurity = getUserSecurity();
        TextEncryption textEncryption = new TextEncryption();

        UserEntity savedUserEntity = this.userDao.getById(userSecurity.getId());

        if(savedUserEntity == null || !savedUserEntity.getPassword().equals(textEncryption.getMD5(currentPassword))){
            throw new AuthorizationException("Access Denied");
        }

        UserEntity userEntityWithUpdatedPassword = userEntityDtoFacade
                .getEntityWithUpdatedPassword(savedUserEntity, newPassword);

        this.userDao.update(userEntityWithUpdatedPassword);
    }

    public UserDTO userPicture(MultipartFile file) throws IOException {
        UserSecurity userSecurity = getUserSecurity();
        UserEntity savedUserEntity = this.userDao.getById(userSecurity.getId());

        String fileName = savedUserEntity.getId()+"."+getFileExtension(file.getOriginalFilename());
        String path = imagePath+"/"+fileName;
        File convertFile = new File(path);

        try {
            convertFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

            ImageResizer imageResizer = new ImageResizer();
            imageResizer.resize(path, path,300);

            // TODO: solve this
//            savedUserEntity.setPictureFileName(fileName);
            this.userDao.update(savedUserEntity);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        }

        return new UserDTO(savedUserEntity);
    }

    private UserSecurity getUserSecurity(){
        UserSecurity userSecurity = UserService.authenticated();

        if(userSecurity == null ){
            throw new AuthorizationException("Access Denied");
        }
        return userSecurity;
    }

    private String getFileExtension(String fullName) {
        if(fullName.isEmpty()){
            return "";
        }
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
