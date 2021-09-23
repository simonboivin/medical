package com.atos.medical.model.security;

import com.atos.medical.model.entities.UsersEntity;
import com.atos.medical.model.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetailsServiceImplementation(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByEmail(s);

        return new UserDetailsImplementation(user);
    }
}
