package com.avantica.proa.Repositories;

import com.avantica.proa.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface UserRepository extends JpaRepository<User, Serializable> {
    User findByEmail(String email);
}
