package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Role;
import io.github.samirsales.exception.AuthorizationException;
import io.github.samirsales.exception.UserUpdateException;
import io.github.samirsales.util.ImageResizer;
import io.github.samirsales.security.UserSS;
import io.github.samirsales.util.TextEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("postgres")
    private UserDao userDao;

    @Value("${uploading.image.path.profiles}")
    private String imagePath;

    public static UserSS authenticated(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public List<UserDTO> getAllUsers(){
        Collection<UserEntity> userEntities = userDao.getAll();
        return userEntities.parallelStream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){

        UserSS userSS = UserService.authenticated();

        if(userSS == null || (!userSS.hashRole(Role.ADMIN) && !id.equals(userSS.getId()))){
            throw new AuthorizationException("Access Denied");
        }

        return new UserDTO(this.userDao.getById(id));
    }

    public UserDTO getUserByAuthentication(UserEntity userEntity) {
        return new UserDTO(this.userDao.getByAuthentication(userEntity));
    }

    public void removeUserById(long id) {
        this.userDao.deleteById(id);
    }

    public void updateUser(UserEntity userEntity){
        TextEncryption textEncryption = new TextEncryption();
        userEntity.setPassword(textEncryption.getMD5(userEntity.getPassword()));

        this.userDao.update(userEntity);
    }

    public void insertUser(UserEntity userEntity) {
        this.userDao.create(userEntity);
    }

    public UserDTO getUserByToken() {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }

        return getUserById(userSS.getId());
    }

    public UserDTO dataUserConfig(UserEntity userEntity) {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }

        UserEntity userEntitySaved = this.userDao.getById(userSS.getId());

        userEntitySaved.setName(userEntity.getName());
        userEntitySaved.setGender(userEntity.getGender());

        String password = userEntitySaved.getPassword();

        UserEntity userEntityByLogin = this.userDao.getByLogin(userEntity.getLogin(), false);
        if(userEntityByLogin == null || userEntityByLogin.getId().equals(userEntitySaved.getId())){
            userEntitySaved.setLogin(userEntity.getLogin());
        }else {
            throw new UserUpdateException("Login not available");
        }

        UserEntity userEntityByEmail = this.userDao.getByEmail(userEntity.getEmail(), false);
        if(userEntityByEmail == null || userEntityByEmail.getId().equals(userEntitySaved.getId())){
            userEntitySaved.setEmail(userEntity.getEmail());
        }else {
            throw new UserUpdateException("E-mail not available");
        }

        userEntitySaved.setPassword(password);
        this.userDao.update(userEntitySaved);

        return new UserDTO(userEntitySaved);
    }

    public void setUserPassword(String currentPassword, String newPassword){
        UserSS userSS = UserService.authenticated();
        TextEncryption textEncryption = new TextEncryption();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }

        UserEntity savedUserEntity = this.userDao.getById(userSS.getId());

        if(savedUserEntity == null || !savedUserEntity.getPassword().equals(textEncryption.getMD5(currentPassword))){
            throw new AuthorizationException("Access Denied");
        }

        savedUserEntity.setPassword(textEncryption.getMD5(newPassword));
        this.userDao.update(savedUserEntity);
    }

    public UserDTO userPicture(MultipartFile file) throws IOException {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }
        UserEntity savedUserEntity = this.userDao.getById(userSS.getId());

        String fileName = savedUserEntity.getId()+"."+getFileExtension(file.getOriginalFilename());
        String path = imagePath+"/"+fileName;
        File convertFile = new File(path);

        System.out.println(path);

        try {
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();

            ImageResizer imgResizer = new ImageResizer();
            imgResizer.resize(path, path,300);

            // TODO: solve this
//            savedUserEntity.setPictureFileName(fileName);
            this.userDao.update(savedUserEntity);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        }

        return new UserDTO(savedUserEntity);
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
