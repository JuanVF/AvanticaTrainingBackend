package com.avantica.proa.Repositories;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    List<Resource> findAllByTopic(Topic topic);
}
