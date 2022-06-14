package com.codegym.cms.repository;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findAllByName(String name);
    Iterable<Product> findAllByPriceBetween(Long from, Long to);
    Iterable<Product> findAllByNameContaining(String name);
}
