package com.example.batch.service;

import com.example.batch.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list){
        //执行SQL,输出查到的数据
        List<?> resultList = jdbcTemplate.queryForList("select * from batch_job_seq");
        for (Product product : list) {
            String main_cat = product.getMain_cat();
            String price = product.getPrice();
            String title = product.getTitle();
            String asin = product.getAsin();
            List<String> categories = product.getCategory();
            List<String> urls = product.getImageURLHighRes();
            try {
                jdbcTemplate.execute(String.format("insert into products " +
                        "VALUES ('%s', '%s', '%s', '%f', '%s', '%s')",
                        main_cat, title, asin, parsePrice(price), categories.get(0), urls.get(0)));
            }  catch (Exception ignored) {
            }
        }
        list.stream().forEach(System.out::println);
        System.out.println("chunk written");
    }

    private double parsePrice(String price){
        if(price == null || price.equals("")){
            return 0;
        }else{
            String pattern = "([1-9]d*.?d*)|(0.d*[1-9])";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            // 现在创建 matcher 对象
            Matcher m = r.matcher(price);
            if (m.find( )) {
                return Double.parseDouble(m.group(1));
            } else {
                return 0;
            }
        }
    }
}
