package com.avantica.proa.Services;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource save(Resource resource){
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> findAll(){
        return resourceRepository.findAll();
    }

    @Override
    public Resource findById(long id) throws Exception{
        return resourceRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Resource update(Resource resource){
        return resourceRepository.save(resource);
    }

    @Override
    public void delete(long id) throws Exception{
        resourceRepository.deleteById(id);
    }
}
