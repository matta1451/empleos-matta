package com.example.aplicacionempleos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class AplicacionEmpleosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AplicacionEmpleosApplication.class, args);
    }

}
