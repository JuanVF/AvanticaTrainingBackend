package com.avantica.proa.Services;

import com.avantica.proa.Models.Topic;
import com.avantica.proa.Repositories.ITopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements ITopicService{
    private ITopicRepository topicRepository;

    public TopicService(ITopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic save(Topic topic){
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(long id) throws Exception{
        return topicRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Topic update(Topic topic){
        return topicRepository.save(topic);
    }

    @Override
    public void delete(long id) throws Exception{
        topicRepository.deleteById(id);
    }
}
