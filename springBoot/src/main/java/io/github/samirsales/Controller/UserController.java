package io.github.samirsales.Controller;

import io.github.samirsales.Entity.Dto.UserDTO;
import io.github.samirsales.Entity.User;
import io.github.samirsales.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeUserById(@PathVariable("id") long id){
        userService.removeUserById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/setting", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO dataUserConfig(@RequestBody User user){
        return userService.dataUserConfig(user);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/update_picture", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        return userService.userPicture(file);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/update_password", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateUserPassword(@RequestParam("currentPassword") String currentPassword,
                                                     @RequestParam("newPassword") String newPassword) throws Exception {
        userService.setUserPassword(currentPassword, newPassword);
        return new ResponseEntity<>("The user's password has been updated successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public UserDTO getUserByToken(){
        return userService.getUserByToken();
    }

}
