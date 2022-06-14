package com.codegym.cms.controller;


import com.codegym.cms.model.Category;
import com.codegym.cms.model.Product;
import com.codegym.cms.service.category.ICategoryService;
import com.codegym.cms.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/showPriceAsc")
    public ResponseEntity<Iterable<Product>> findAllProductAsc() {
        List<Product> products = (List<Product>) productService.showListAsc();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/showNewList")
    public ResponseEntity<Iterable<Product>> showNewList() {
        List<Product> products = (List<Product>) productService.showNewList();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> findAllByName(@RequestParam("name") String name) {
        List<Product> products = (List<Product>) productService.findByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<Iterable<Product>> findByNameContaining(@RequestParam("name") String name) {
        List<Product> products = (List<Product>) productService.findByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Product>> findAllPriceBetween(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        List<Product> products = (List<Product>) productService.findAllByPriceBetween(from, to);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<Iterable<Product>> findAllByCategory(@RequestParam Long id) {
        List<Product> products = (List<Product>) productService.findAllByCategory((Category) categoryService.findById(id).get());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        if (product.getId() == null || product.getCategory() == null || product.getName() == null || product.getPrice() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }
}
