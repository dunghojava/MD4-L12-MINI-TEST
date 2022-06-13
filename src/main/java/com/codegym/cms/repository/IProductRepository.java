package com.codegym.cms.repository;

import com.codegym.cms.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByName(String name);
    Iterable<Product> findAllByPriceBetween(int from, int to);
}
