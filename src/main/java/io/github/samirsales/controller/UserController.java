package io.github.samirsales.controller;

import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.exception.UserUpdateException;
import io.github.samirsales.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@SuppressWarnings("unused")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll(){
        logger.info("Getting all the users.");
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable("id") long id){
        logger.info("Getting user by ID = {}.", id);
        Optional<UserDTO> optionalUserDTO = userService.getById(id);

        return optionalUserDTO.<ResponseEntity<Object>>map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/authenticated", method = RequestMethod.GET)
    public UserDTO getAuthenticatedUser(){
        logger.info("Getting authenticated user.");
        return userService.getAuthenticatedUser();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id){
        logger.info("Deleting user by ID = {}.", id);

        try {
            userService.deleteById(id);
            String successMessage = "The user has been deleted successfully.";
            logger.info(successMessage);
            return new ResponseEntity<>(successMessage, HttpStatus.OK);

        } catch (NotFoundException e) {
            logger.error("Error trying to delete user [ID = {}].", id);
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserDTO userDTO){
        logger.info("Updating user [ID = {}].", userDTO.getId());
        userService.update(userDTO);
    }

    final private String PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE = "The user's password has been deleted successfully";

    @RequestMapping(value = "/update_authenticated", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  updateAuthenticatedUser(@RequestBody UserDTO authenticatedUserDTO){

        try{
            userService.getUpdatedAuthenticatedUserByDTO(authenticatedUserDTO);
        }catch (UserUpdateException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //TODO
        }
        return new ResponseEntity<>(PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_password",
        method = RequestMethod.PUT,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateUserPassword(
        @RequestParam("currentPassword") String currentPassword,
        @RequestParam("newPassword") String newPassword){

        userService.setUserPassword(currentPassword, newPassword); //TODO
        return new ResponseEntity<>(PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_picture",
        method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO uploadImageFile(@RequestParam("file") MultipartFile file) throws Exception {
        return userService.userPicture(file);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody UserDTO userDTO){
        userService.insert(userDTO);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
