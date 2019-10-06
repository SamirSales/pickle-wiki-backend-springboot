package io.github.samirsales.controller;

import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.exception.UserUpdateException;
import io.github.samirsales.service.UserService;
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
@SuppressWarnings("unused")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getById(@PathVariable("id") long id){
        return userService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id){
        userService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/authenticated", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  updateAuthenticatedUser(@RequestBody UserDTO authenticatedUserDTO){

        try{
            userService.getUpdatedAuthenticatedUserByDTO(authenticatedUserDTO);
        }catch (UserUpdateException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The user's password has been updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/update_picture",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO uploadImageFile(@RequestParam("file") MultipartFile file) throws Exception {
        return userService.userPicture(file);
    }

    @RequestMapping(value = "/update_password",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateUserPassword(
        @RequestParam("currentPassword") String currentPassword,
        @RequestParam("newPassword") String newPassword){

        userService.setUserPassword(currentPassword, newPassword);
        return new ResponseEntity<>("The user's password has been updated successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody UserDTO userDTO){
        userService.create(userDTO);
    }

    @RequestMapping(value = "/authenticated", method = RequestMethod.POST)
    public UserDTO getAuthenticatedUser(){
        return userService.getAuthenticatedUser();
    }

}
