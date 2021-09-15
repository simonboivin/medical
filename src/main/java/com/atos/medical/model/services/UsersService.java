package com.atos.medical.model.services;

import com.atos.medical.model.entities.PatientsEntity;
import com.atos.medical.model.entities.UsersEntity;
import com.atos.medical.model.repositories.UsersRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
     * Return an Users find by its ID
     *
     * @param id ID of the user
     */
    public Optional<UsersEntity> getUserById(int id) {
        return usersRepository.findById(id);
    }

    /**
     * Set all parameter of user (part of addUser and updateUserById)
     */
    private UsersEntity setUser(UsersEntity user, String name, String email, String password, String roles, String photoUser) {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        user.setPhotoUser(photoUser);
        return user;
    }

    /**
     * Add an user in the database
     */
    @Transactional
    public UsersEntity addUser(String name, String email, String password, String roles, String photoUser) {
        UsersEntity user = setUser(new UsersEntity(), name, email, password, roles, photoUser);
        usersRepository.save(user);
        return user;
    }


    /**
     * Edit an user
     *
     * @param id        ID of the user to edit
     * @param name      New name
     * @param email     New email
     * @param password  New password
     * @param roles     New roles
     * @param photoUser New photoUser path
     * @return the user updated
     */
    @Transactional
    public UsersEntity updateUserById(int id, String name, String email, String password, String roles, String photoUser) {
        Optional<UsersEntity> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            return usersRepository.save(setUser(userOptional.get(), name, email, password, roles, photoUser));
        } else {
            throw new ObjectNotFoundException(id, "User not found");
        }
    }

    /**
     * Delete the user
     *
     * @param id Id of the user to delete
     */
    @Transactional
    public void deleteUserById(int id) {
        Optional<UsersEntity> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            usersRepository.delete(userOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "User not found");
        }
    }

}
