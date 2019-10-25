package com.avantica.proa.Services;

import com.avantica.proa.Models.User;
import com.avantica.proa.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) throws Exception{
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User update(User user){
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) throws Exception{
        userRepository.deleteById(id);
    }
}
