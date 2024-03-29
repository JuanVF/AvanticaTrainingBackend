package com.avantica.proa.Services;

import com.avantica.proa.Models.Resource;

import java.util.List;

public interface ResourceService {
    Resource save(Resource resource);

    List<Resource> findAll();

    Resource findById(long id) throws Exception;

    Resource update(Resource resource);

    void delete(long id) throws Exception;
}
