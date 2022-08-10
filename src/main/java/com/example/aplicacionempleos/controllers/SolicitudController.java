package com.example.aplicacionempleos.controllers;

import com.example.aplicacionempleos.editor.RutasAbsolutas;
import com.example.aplicacionempleos.models.entity.Solicitud;
import com.example.aplicacionempleos.models.entity.Vacante;
import com.example.aplicacionempleos.services.interfaces.ISolicitudService;
import com.example.aplicacionempleos.services.interfaces.IUsuariosService;
import com.example.aplicacionempleos.services.interfaces.IVacanteService;
import com.example.aplicacionempleos.util.MessagesError;
import com.example.aplicacionempleos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("vacante")
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;
    @Autowired
    private IVacanteService vacanteService;
    @Autowired
    private IUsuariosService usuariosService;
    @Autowired
    private RutasAbsolutas rutasAbsolutas;

    @GetMapping("/solicitud/{id}")
    public String showSolicitud(@PathVariable("id") Long id, Model model){
        Vacante vacante = vacanteService.findById(id);
        Solicitud solicitud = new Solicitud();
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("vacante", vacante);
        return "formSolicitud";
    }

    @PostMapping("/save_solicitud")
    public String saveForm(Solicitud solicitud, Vacante vacante, Authentication authentication,
                           @RequestParam("archivoCV") MultipartFile multipartFile, SessionStatus sessionStatus,
                           RedirectAttributes redirectAttributes){
        solicitud.actualizarForaneasYFecha(vacante, usuariosService.findByUsername(authentication.getName()), new Date());
        if(!multipartFile.isEmpty()){
            String directorio = Utileria.saveFile(multipartFile, rutasAbsolutas.AbsoluteDocument());
            if(directorio != null){
                solicitud.setArchivo(directorio);
            }
        }
        solicitudService.save(solicitud);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("msg", "Vacante aplicada exitosamente.");
        return "redirect:/";
    }

    @GetMapping("/lista_solicitudes")
    public String listaSolicitudes(Model model) {
        List<Solicitud> solicitudes = solicitudService.findAll();
        model.addAttribute("solicitudes", solicitudes);
        return "listSolicitudes";
    }

    @GetMapping("/lista_solicitudes/eliminar/{id}")
    public String listaSolicitudesEliminar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            solicitudService.deleteById(id);
            redirectAttributes.addFlashAttribute("solicitud_eliminada", "Solicitud eliminada exitosamente.");
        }catch(Exception e){
            String errorMessage = MessagesError.ERROR_MESSAGE;
            redirectAttributes.addFlashAttribute("mensaje_error", errorMessage);
        }
        return "redirect:/lista_solicitudes";
    }
}
