package com.grocery.demo.Service;

import com.grocery.demo.Model.User;
import com.grocery.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void saveUser(User user) {
        //user.setEnabled(false);
        user.setPword(new BCryptPasswordEncoder().encode(user.getPword()));
        user.setRoles(user.getRoles());
        userRepository.save(user);


    }

    public User findByEmail(String email){
        return userRepository.findByEmailIgnoreCase(email);
    }

}