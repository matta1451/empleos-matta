package com.example.aplicacionempleos.configuration;

import com.example.aplicacionempleos.editor.RutasAbsolutas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RutasAbsolutas rutasAbsolutas;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*Properties properties = new Properties();
        properties.put("spring.servlet.multipart.location", rutasAbsolutas.AbsoluteTemporal() + "\\");*/
        registry.addResourceHandler("/logos/**").addResourceLocations("rutasAbsolutas.ResourceImage()");
        registry.addResourceHandler("/documents/**").addResourceLocations(rutasAbsolutas.ResourceDocument());
    }
}
