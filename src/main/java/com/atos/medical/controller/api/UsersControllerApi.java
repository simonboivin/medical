package com.atos.medical.controller.api;

import com.atos.medical.model.entities.UsersEntity;
import com.atos.medical.model.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersControllerApi {

    private final UsersService usersService;

    public UsersControllerApi(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Get list of all users
     */
    @GetMapping(path = "", produces = "application/json")
    public List<UsersEntity> getUserListApi() {
        return usersService.getAllUsers();
    }

    /**
     * Get an user
     * @param id of the user
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public UsersEntity getUserByIdApi(@PathVariable("id") int id) {
        Optional<UsersEntity> userOptional = usersService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user is not found");
        }
    }

    /**
     * Add a new user
     */
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<UsersEntity> addUserApi(@RequestBody UsersEntity userInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                usersService.addUser(userInput.getName(),
                        userInput.getEmail(),
                        userInput.getPassword(),
                        userInput.getRoles(),
                        userInput.getPhotoUser()));
    }

    /**
     * Update an user
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UsersEntity> updateUserApi(@PathVariable("id") int id, @RequestBody UsersEntity userInput) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                usersService.updateUserById(id ,userInput.getName(),
                        userInput.getEmail(),
                        userInput.getPassword(),
                        userInput.getRoles(),
                        userInput.getPhotoUser()));
    }

    /**
     * Delete an user
     */
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUserApi(@PathVariable("id") int id) {
        usersService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User " + id +" deleted");
    }

}
