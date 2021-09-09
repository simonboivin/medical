package com.atos.medical.model.services;

import com.atos.medical.model.entities.UsersEntity;
import com.atos.medical.model.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
            this.usersRepository = usersRepository;
    }

        /**
         * Return the list of all users
         */
    public List<UsersEntity> getAllUsers() {
        return (List<UsersEntity>) usersRepository.findAll();
    }

    /**
     * Add an user in the database
     */
    @Transactional
    public UsersEntity addUser(String name, String email, String password, String roles, String photoUser) {
            UsersEntity user = new UsersEntity();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(roles);
            user.setPhotoUser(photoUser);
            usersRepository.save(user);
            return user;
    }

}
