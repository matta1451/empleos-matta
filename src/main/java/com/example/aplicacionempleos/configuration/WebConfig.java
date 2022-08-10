package com.example.aplicacionempleos.configuration;

import com.example.aplicacionempleos.editor.RutasAbsolutas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RutasAbsolutas rutasAbsolutas;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*Properties properties = new Properties();
        properties.put("spring.servlet.multipart.location", rutasAbsolutas.AbsoluteTemporal() + "\\");*/
        registry.addResourceHandler("/logos/**").addResourceLocations(rutasAbsolutas.ResourceImage());
        registry.addResourceHandler("/documents/**").addResourceLocations(rutasAbsolutas.ResourceDocument());
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap");
        registry.addResourceHandler("/documents/**").addResourceLocations("/documents");
        registry.addResourceHandler("/images/**").addResourceLocations("/images");
        registry.addResourceHandler("/tinymce/**").addResourceLocations("/tinymce");
    }

    @Bean
    public ClassLoaderTemplateResolver ClassLoaderTemplateResolver(){
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix("/templates/");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderTemplateResolver.setCharacterEncoding("UTF-8");
        classLoaderTemplateResolver.setOrder(1);
        classLoaderTemplateResolver.setCheckExistence(true);
        return classLoaderTemplateResolver;
    }
}
