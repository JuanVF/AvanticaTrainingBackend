package com.avantica.proa.Repositories;

import com.avantica.proa.Models.FBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FBUserRepository extends JpaRepository<FBUser, Long> {
    FBUser findByEmail(String email);
    void deleteByEmail(String email);
}
