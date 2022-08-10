package com.example.aplicacionempleos.editor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component("RutasAbsolutas")
public class RutasAbsolutas {

//    @Value("${empleosaap.images.path}")
//    private String ruta_imagen;
//
//    @Value("${empleosapp.archivocv.path}")
//    private String ruta_documentos;
//
//    @Value("${empleosapp.temporal.path}")
//    private String ruta_temporal;

    public String ResourceImage() {
        return Paths.get("src/main/resources/static/images").toAbsolutePath().toUri().toString();
    }
    public String ResourceDocument() {
        return Paths.get("src/main/resources/static/documents/").toAbsolutePath().toUri().toString();
    }
    public String AbsoluteImage() {
        return Paths.get("classpath:/static/images/").toAbsolutePath().toString();
    }
    public String AbsoluteDocument() {
        return Paths.get("src/main/resources/static/documents/").toAbsolutePath().toString();
    }
    public String AbsoluteTemporal() {
        return Paths.get("src/main/resources/static/tmp/").toAbsolutePath().toString();
    }
    public String AbsoluteStaticResources(){
        return Paths.get(("src/main/resources/static/")).toUri().toString();
    }

}
