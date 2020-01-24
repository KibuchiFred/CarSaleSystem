package com.grocery.demo.Service;


import com.grocery.demo.Model.Role;
import com.grocery.demo.Model.User;
import com.grocery.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;//has a method to find a user by username

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUname(s);

        //if a username that does not exist is input by the user
        if(user == null) {
            throw new UsernameNotFoundException("User not found" + s);
        }
        else {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            for(Role role : user.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(user.getUname(),
                    user.getPword(),grantedAuthorities);
        }


    }
}
