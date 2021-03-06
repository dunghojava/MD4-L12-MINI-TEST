package com.codegym.cms.service.product;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.Product;
import com.codegym.cms.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public Iterable<Product> findByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllByPriceBetween(Long from, Long to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> showListAsc() {
        return productRepository.showListAsc();
    }

    @Override
    public Iterable<Product> showNewList() {
        return productRepository.showNewList();
    }


}
