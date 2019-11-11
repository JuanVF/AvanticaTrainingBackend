package com.avantica.proa.Services;


import com.avantica.proa.FBTokenUtils;
import com.avantica.proa.Models.User;
import com.avantica.proa.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),buildGrantedAuthority(user.getRole()));
    }

    private List<GrantedAuthority> buildGrantedAuthority(byte rol){
        String[] roles = {"READER","USER","ADMINISTRATOR"};
        List<GrantedAuthority> auths = new ArrayList<>();

        for(int i = 0; i<rol; i++){
            auths.add(new SimpleGrantedAuthority(roles[i]));
        }
        return auths;
    }
}
