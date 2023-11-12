package com.example.shop14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductDatabaseInitializer implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
