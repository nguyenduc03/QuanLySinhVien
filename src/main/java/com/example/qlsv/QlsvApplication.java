package com.example.qlsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QlsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(QlsvApplication.class, args);
    }

}
