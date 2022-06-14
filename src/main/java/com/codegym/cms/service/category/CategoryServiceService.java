package com.codegym.cms.service.category;

import com.codegym.cms.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Optional findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
