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

    private UsersService usersService;

    public UsersControllerApi(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<UsersEntity> getUserListApi() {
        return usersService.getAllUsers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public UsersEntity getUserByIdApi(@PathVariable("id") int id) {
        Optional<UsersEntity> userOptional = usersService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user is not found");
        }
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<UsersEntity> addUserApi(@RequestBody UsersEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUser(user));
    }

}
