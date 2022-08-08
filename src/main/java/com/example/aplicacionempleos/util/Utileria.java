package com.example.aplicacionempleos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utileria {
    public static String saveFile(MultipartFile multipartFile, String ruta){
        String nombre_archivo = multipartFile.getOriginalFilename();
        assert nombre_archivo != null;
        nombre_archivo = nombre_archivo.replace(" ", "-");
        String nombre_final = randomAlfaNumerics(8).concat(nombre_archivo);
        try{
            File file = new File(ruta + "\\" + nombre_final);
            multipartFile.transferTo(file);
            return nombre_final;
        }catch (IOException e){
            return null;
        }
    }

    public static String randomAlfaNumerics(int count){
        String caracters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        while (count-- != 0){
            int character = (int) (Math.random() * caracters.length());
            stringBuilder.append(caracters.charAt(character));
        }
        return stringBuilder.toString();
    }
}
