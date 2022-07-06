package com.example.webpos.biz;

import com.example.webpos.db.AmazonDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PosServiceImp implements PosService, Serializable {

    private AmazonDB amazonDB;

    @Autowired
    public void setPosDB(AmazonDB amazonDB) {
        this.amazonDB = amazonDB;
    }


    @Override
    public Product randomProduct() {
        return products().get(ThreadLocalRandom.current().nextInt(0, products().size()));
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public Cart add(Cart cart, Product product, int amount) {
        return add(cart, product.getId(), amount);
    }

    @Override
    public Cart add(Cart cart, String productId, int amount) {

        Product product = amazonDB.getProduct(productId);
        if (product == null) return cart;

        cart.addItem(new Item(product, amount));
        return cart;
    }

    @Override
    public Cart delete(Cart cart, String productId){
        cart.deleteItem(productId);
        return cart;
    }

    @Override
    public Cart modify(Cart cart, String productId, int amount){
        cart.modifyItem(productId, amount);
        return cart;
    }

    @Override
    public Cart empty(Cart cart){
        cart.emptyCart();
        return cart;
    }

    @Override
    public List<Product> products() {
        return amazonDB.getProducts();
    }
}
