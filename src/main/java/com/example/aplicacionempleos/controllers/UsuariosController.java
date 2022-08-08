package com.example.aplicacionempleos.controllers;

import com.example.aplicacionempleos.models.entity.Usuario;
import com.example.aplicacionempleos.services.interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    //@Value("${error.message}")
    private String errorMessage = "Ha ocurrido un error al intentar eliminar este registro. La causa de esto podria ser, que este registro \\\n" +
            "  este vinculado con alguno otro y por ello al intentar eliminarlo, se estaria eliminando todos aquellos que esten \\\n" +
            "  enlazados a este.";

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("usuarios", usuariosService.findAll());
        return "listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try{
            usuariosService.deleteById(id);
            attributes.addFlashAttribute("msg", "Usuario eliminado exitosamente");
        }catch (Exception e){
            attributes.addFlashAttribute("mensaje_error", errorMessage);
        }
        return "redirect:/usuarios/index";
    }

    @GetMapping("/bloquear/{id}")
    public String bloquearAcceso(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Usuario usuario = usuariosService.findById(id);
        if(usuario.getEstatus() == 1){
            System.out.println("Estatus antes del cambio: " + usuario.getEstatus());
            usuario.setEstatus((byte) 0);
            System.out.println("Estatus después del cambio: " + usuario.getEstatus());
            redirectAttributes.addFlashAttribute("msg", "Cuenta de usuario bloqueada!.");
        }else{
            redirectAttributes.addFlashAttribute("msg", "Esta cuenta de usuario ya estaba bloqueada!.");
        }
        usuariosService.save(usuario);
        return "redirect:/usuarios/index";
    }

    @GetMapping("/desbloquear/{id}")
    public String desbloquearAcceso(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Usuario usuario = usuariosService.findById(id);
        if(usuario.getEstatus() == 0){
            usuario.setEstatus((byte) 1);
            redirectAttributes.addFlashAttribute("msg", "Cuenta de usuario desbloqueada!.");
        }else{
            redirectAttributes.addFlashAttribute("msg", "Esta cuenta de usuario ya estaba desbloqueada!.");
        }
        usuariosService.save(usuario);
        return "redirect:/usuarios/index";
    }
}
