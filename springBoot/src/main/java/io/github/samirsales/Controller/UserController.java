package io.github.samirsales.Controller;

import io.github.samirsales.Entity.User;
import io.github.samirsales.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByAuthentication(@RequestBody User user){
        return userService.getUserByAuthentication(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeUserById(@PathVariable("id") long id){
        userService.removeUserById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }
}
