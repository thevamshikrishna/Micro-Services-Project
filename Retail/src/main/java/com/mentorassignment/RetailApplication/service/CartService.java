package com.mentorassignment.RetailApplication.service;

import com.mentorassignment.RetailApplication.exception.CartNotFoundException;
import com.mentorassignment.RetailApplication.model.Cart;
import com.mentorassignment.RetailApplication.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart getCartbyId(Long id){
        return cartRepository.findById(id)
                .orElseThrow(()->new CartNotFoundException("Cart Not Found"));
    }

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }


}