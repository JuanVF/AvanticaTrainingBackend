package com.avantica.proa.Services;

import com.avantica.proa.Models.Topic;

import java.util.List;

public interface TopicService {
    Topic save(Topic topic);
    List<Topic> findAll();
    Topic findById(long id) throws Exception;
    Topic update(Topic topic);
    void delete(long id) throws Exception;
}
