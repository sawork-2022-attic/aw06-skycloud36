package com.example.webpos.db;

import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AmazonDB implements PosDB{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getProducts() {
        String sql = "select * from products";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        List<Product> products = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Product product = new Product();
            product.setId(map.get("asin").toString());
            product.setName(map.get("title").toString());
            product.setPrice(Double.parseDouble(map.get("price").toString()));
            product.setImage(map.get("imageURLHighRe").toString());
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        String sql = String.format("select * from products where asin='%s'", productId);
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Product product = new Product();
            product.setId(map.get("asin").toString());
            product.setName(map.get("title").toString());
            product.setPrice(Double.parseDouble(map.get("price").toString()));
            product.setImage(map.get("imageURLHighRe").toString());
            return product;
        }
        return null;
    }
}
