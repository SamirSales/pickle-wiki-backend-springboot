package io.github.samirsales.controller;

import io.github.samirsales.exception.UserUpdateException;
import io.github.samirsales.model.dto.user.UserCreationDTO;
import io.github.samirsales.model.dto.user.UserDTO;
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

import javax.validation.Valid;
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id){
        logger.info("Getting user by ID = {}.", id);
        Optional<UserDTO> optionalUserDTO = userService.getById(id);

        return optionalUserDTO.<ResponseEntity<Object>>map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/authenticated")
    public ResponseEntity<UserDTO> getAuthenticatedUser(){
        logger.info("Getting authenticated user.");
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        logger.info("Deleting user by ID = {}.", id);

        try {
            userService.deleteById(id);
            String successMessage = "The user has been deleted successfully.";
            logger.info(successMessage);
            return ResponseEntity.ok(successMessage);

        } catch (NotFoundException e) {
            logger.error("Error trying to delete user [ID = {}].", id);
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid UserDTO userDTO){
        logger.info("Updating user [ID = {}].", userDTO.getId());
        userService.update(userDTO);
        return ResponseEntity.ok("User has been updated successfully");
    }

    final private String PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE = "The user's password has been deleted successfully";

    @PutMapping(value = "/update_authenticated", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  updateAuthenticatedUser(@RequestBody UserDTO authenticatedUserDTO){

        try{
            userService.getUpdatedAuthenticatedUserByDTO(authenticatedUserDTO);
        }catch (UserUpdateException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //TODO
        }
        return new ResponseEntity<>(PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
    }

    @PutMapping(value = "/update_password", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateUserPassword(
        @RequestParam("currentPassword") String currentPassword,
        @RequestParam("newPassword") String newPassword){

        userService.setUserPassword(currentPassword, newPassword); //TODO
        return new ResponseEntity<>(PASSWORD_UPDATED_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
    }

    @PutMapping(value = "/update_picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDTO uploadImageFile(@RequestParam("file") MultipartFile file) throws Exception {
        return userService.userPicture(file);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody @Valid UserCreationDTO userDTO){
        userService.insert(userDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
