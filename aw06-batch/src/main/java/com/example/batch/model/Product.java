package com.example.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String main_cat;

    private String title;

    private String asin;

    private String price;

    private List<String> category;

    private List<String> imageURLHighRes;

    @Override
    public String toString(){
        return (String.format("main_cat:\t%s\n", main_cat) +
                String.format("title:\t%s\n", title) +
                String.format("asin:\t%s\n", asin) +
                String.format("price:\t%s\n", price) +
                "category:" + category +
                "\nimageURLHighRes" + imageURLHighRes);
    }
}
