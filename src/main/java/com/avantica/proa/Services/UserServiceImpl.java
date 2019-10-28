package com.avantica.proa.Services;

import com.avantica.proa.Models.User;
import com.avantica.proa.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepository;

    public UserServiceImpl(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }

    @Override
    public User save(User user){
        return UserRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return UserRepository.findAll();
    }

    @Override
    public User findById(long id) throws Exception{
        return UserRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User update(User user){
        return UserRepository.save(user);
    }

    @Override
    public void delete(long id) throws Exception{
        UserRepository.deleteById(id);
    }
}
