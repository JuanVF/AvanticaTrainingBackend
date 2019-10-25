package com.avantica.proa.Repositories;

import com.avantica.proa.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResourceRepository extends JpaRepository<Resource,Long> {
}
