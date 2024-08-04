package com.hegde.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hegde.storage"})
public class StorageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

}
