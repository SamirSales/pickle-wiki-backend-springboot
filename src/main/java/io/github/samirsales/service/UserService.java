package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.enums.PermissionType;
import io.github.samirsales.model.entity.User;
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
        Collection<User> users = userDao.getAll();
        return users.parallelStream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){

        UserSS userSS = UserService.authenticated();

        if(userSS == null || (!userSS.hashRole(PermissionType.ADMIN) && !id.equals(userSS.getId()))){
            throw new AuthorizationException("Access Denied");
        }

        return new UserDTO(this.userDao.getById(id));
    }

    public UserDTO getUserByAuthentication(User user) {
        return new UserDTO(this.userDao.getByAuthentication(user));
    }

    public void removeUserById(long id) {
        this.userDao.deleteById(id);
    }

    public void updateUser(User user){
        TextEncryption textEncryption = new TextEncryption();
        user.setPassword(textEncryption.getMD5(user.getPassword()));

        this.userDao.update(user);
    }

    public void insertUser(User user) {
        this.userDao.insert(user);
    }

    public UserDTO getUserByToken() {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }

        return getUserById(userSS.getId());
    }

    public UserDTO dataUserConfig(User user) {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }

        User userSaved = this.userDao.getById(userSS.getId());

        userSaved.setName(user.getName());
        userSaved.setGender(user.getGender());

        String password = userSaved.getPassword();

        User userByLogin = this.userDao.getByLogin(user.getLogin(), false);
        if(userByLogin == null || userByLogin.getId().equals(userSaved.getId())){
            userSaved.setLogin(user.getLogin());
        }else {
            throw new UserUpdateException("Login not available");
        }

        User userByEmail = this.userDao.getByEmail(user.getEmail(), false);
        if(userByEmail == null || userByEmail.getId().equals(userSaved.getId())){
            userSaved.setEmail(user.getEmail());
        }else {
            throw new UserUpdateException("E-mail not available");
        }

        userSaved.setPassword(password);
        this.userDao.update(userSaved);

        return new UserDTO(userSaved);
    }

    public void setUserPassword(String currentPassword, String newPassword){
        UserSS userSS = UserService.authenticated();
        TextEncryption textEncryption = new TextEncryption();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }

        User savedUser = this.userDao.getById(userSS.getId());

        if(savedUser == null || !savedUser.getPassword().equals(textEncryption.getMD5(currentPassword))){
            throw new AuthorizationException("Access Denied");
        }

        savedUser.setPassword(textEncryption.getMD5(newPassword));
        this.userDao.update(savedUser);
    }

    public UserDTO userPicture(MultipartFile file) throws IOException {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }
        User savedUser = this.userDao.getById(userSS.getId());

        String fileName = savedUser.getId()+"."+getFileExtension(file.getOriginalFilename());
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

            savedUser.setPictureFileName(fileName);
            this.userDao.update(savedUser);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        }

        return new UserDTO(savedUser);
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
