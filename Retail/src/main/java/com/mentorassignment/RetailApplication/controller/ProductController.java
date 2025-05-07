package com.mentorassignment.RetailApplication.controller;

import com.mentorassignment.RetailApplication.model.Product;
import com.mentorassignment.RetailApplication.repository.ProductRepository;
import com.mentorassignment.RetailApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
class ProductController{
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/get")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
       productService.saveProduct(product);
    }

    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable("id") Long id ){
      productService.deleteProduct(id);
    }

    @PutMapping("update/{id}")
    public void updateProduct(@PathVariable String id,@RequestBody Product product){
        productService.saveProduct(product);
    }
}
















//@RestController
//@RequestMapping("/product")
//public class ProductController {
//    private final ProductRepository repo;
//
//    @Autowired
//    public ProductController(ProductRepository repo) {
//        this.repo = repo;
//    }
//
//    @GetMapping("/get")
//    public List<Product> getProducts(){
//        return repo.findAll();
//    }
//
//    @PostMapping("/add")
//    public Product addProduct(@RequestBody Product product){
//        return  repo.save(product);
//    }
//
//    @DeleteMapping("remove/{id}")
//    public void deleteProduct(@PathVariable("id") Product product){
//       repo.delete(product);
//    }
//
//    @PutMapping("update/{id}")
//    public void updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
//        repo.save(product);
//    }
//
//}
