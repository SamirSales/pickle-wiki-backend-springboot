package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.exception.AuthorizationException;
import io.github.samirsales.facade.UserEntityDtoFacade;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.security.UserSecurity;
import io.github.samirsales.util.ImageResizer;
import io.github.samirsales.util.TextEncryption;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public UserDTO getById(Long id) throws NotFoundException {
        Optional<UserEntity> userEntityOptional = userDao.getById(id);

        if(userEntityOptional.isPresent()){
            return new UserDTO(userEntityOptional.get());
        }

        String exceptionMessage = getNotFoundExceptionMessageByUserId(id);
        throw new NotFoundException(exceptionMessage);
    }

    public void create(UserDTO userDTO) {
        UserEntity userEntity = userEntityDtoFacade.getActiveEntitySetByDTO(userDTO);
        this.userDao.create(userEntity);
    }

    public void update(UserDTO userDTO){
        UserEntity userEntity = userEntityDtoFacade.getActiveEntitySetByIdAndDTO(userDTO.getId(), userDTO);
        this.userDao.update(userEntity);
    }

    public void deleteById(long id) throws NotFoundException {
        Optional<UserEntity> userEntityOptional = userDao.getById(id);

        if(userEntityOptional.isPresent()){
            this.userDao.deleteById(id);
        }else{
            String exceptionMessage = getNotFoundExceptionMessageByUserId(id);
            throw new NotFoundException(exceptionMessage);
        }
    }

    public UserDTO getAuthenticatedUser() {
        UserSecurity userSecurity = getUserSecurity();
        try {
            return getById(userSecurity.getId());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        String exceptionMessage = getNotFoundExceptionMessageByUserId(userSecurity.getId());
        throw new UsernameNotFoundException(exceptionMessage);
    }

    public UserDTO getUpdatedAuthenticatedUserByDTO(UserDTO userDTO) { //TODO: it needs revision.
        UserSecurity userSecurity = getUserSecurity();
        Long idAuthenticatedUser = userSecurity.getId();

        UserEntity userEntitySetByDTO = userEntityDtoFacade.getActiveEntitySetByIdAndDTO(idAuthenticatedUser, userDTO);
        userDao.update(userEntitySetByDTO);

        Optional<UserEntity> updatedUserEntityOptional = this.userDao.getById(userSecurity.getId());

        if(updatedUserEntityOptional.isPresent()){
            return new UserDTO(updatedUserEntityOptional.get());
        }

        String exceptionMessage = getNotFoundExceptionMessageByUserId(idAuthenticatedUser);
        throw new UsernameNotFoundException(exceptionMessage);
    }

    public void setUserPassword(String currentPassword, String newPassword){ //TODO: it needs revision.
        UserSecurity userSecurity = getUserSecurity();
        Optional<UserEntity> savedUserEntityOptional = this.userDao.getById(userSecurity.getId());

        if(savedUserEntityOptional.isPresent()){
            UserEntity savedUserEntity = savedUserEntityOptional.get();
            TextEncryption textEncryption = new TextEncryption();

            if(!savedUserEntity.getPassword().equals(textEncryption.getMD5(currentPassword))){
                throw new AuthorizationException("Access Denied");
            }

            UserEntity userEntityWithUpdatedPassword = userEntityDtoFacade
                    .getEntityWithUpdatedPassword(savedUserEntity, newPassword);

            this.userDao.update(userEntityWithUpdatedPassword);
        }

        String exceptionMessage = getNotFoundExceptionMessageByUsername(userSecurity.getUsername());
        throw new UsernameNotFoundException(exceptionMessage);
    }

    public UserDTO userPicture(MultipartFile file) throws IOException { //TODO: it needs revision.
        UserSecurity userSecurity = getUserSecurity();
        Optional<UserEntity> savedUserEntityOptional = this.userDao.getById(userSecurity.getId());

        if(savedUserEntityOptional.isPresent()){
            UserEntity savedUserEntity = savedUserEntityOptional.get();
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

        String exceptionMessage = getNotFoundExceptionMessageByUsername(userSecurity.getUsername());
        throw new UsernameNotFoundException(exceptionMessage);
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

    private String getNotFoundExceptionMessageByUserId(long userId){
        return "User not found (id = " + userId + ")";
    }

    private String getNotFoundExceptionMessageByUsername(String username){
        return "User not found (username = " + username + ")";
    }
}
