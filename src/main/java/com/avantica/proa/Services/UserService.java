package com.avantica.proa.Services;

import com.avantica.proa.Models.User;
import com.avantica.proa.Repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository IUserRepository;

    public UserService(IUserRepository IUserRepository){
        this.IUserRepository = IUserRepository;
    }

    @Override
    public User save(User user){
        return IUserRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return IUserRepository.findAll();
    }

    @Override
    public User findById(long id) throws Exception{
        return IUserRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User update(User user){
        return IUserRepository.save(user);
    }

    @Override
    public void delete(long id) throws Exception{
        IUserRepository.deleteById(id);
    }
}
