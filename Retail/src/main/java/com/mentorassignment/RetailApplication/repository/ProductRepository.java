package com.mentorassignment.RetailApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mentorassignment.RetailApplication.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
