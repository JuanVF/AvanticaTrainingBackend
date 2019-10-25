package com.avantica.proa.Services;

import com.avantica.proa.Models.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User findById(long id) throws Exception;
    User update(User user);
    void delete(long id) throws Exception;
}
