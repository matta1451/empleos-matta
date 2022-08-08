package com.example.aplicacionempleos.controllers;

import com.example.aplicacionempleos.editor.RutasAbsolutas;
import com.example.aplicacionempleos.models.entity.Categoria;
import com.example.aplicacionempleos.models.entity.Vacante;
import com.example.aplicacionempleos.services.interfaces.ICategoriasService;
import com.example.aplicacionempleos.services.interfaces.IVacanteService;
import com.example.aplicacionempleos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
@SessionAttributes("vacante")
public class VacanteController {

    @Autowired
    private RutasAbsolutas rutasAbsolutas;
    @Autowired
    private IVacanteService vacanteService;
    @Autowired
    private ICategoriasService categoriasService;

    //@Value("${error.message}")
    private String errorMessage = "Ha ocurrido un error al intentar eliminar este registro. La causa de esto podria ser, que este registro \\n" +
            "  este vinculado con alguno otro y por ello al intentar eliminarlo, se estaria eliminando todos aquellos que esten \\n" +
            "  enlazados a este.";

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

    @ModelAttribute("categorias_all")
    public List<Categoria> categorias_all(){
        return categoriasService.findAll();
    }

    /*
    @GetMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("vacantes", vacanteService.findAll());
        return "listVacantes";
    }
    */

    @GetMapping("/index")
    public String showIndexPageable(Model model, Pageable pageable){
        Page<Vacante> lista = vacanteService.findAllPage(pageable);
        model.addAttribute("vacantes", lista);
        return "listVacantes";
    }

    @GetMapping("/form")
    public String showFormVacante(Model model){
        Vacante vacante = new Vacante();
        model.addAttribute("vacante", vacante);
        model.addAttribute("categorias", categoriasService.findAll());
        return "formVacante";
    }

    @GetMapping("/form/{id}")
    public String updateVacante(@PathVariable("id") Long id, Model model){
        Vacante vacante = vacanteService.findById(id);
        model.addAttribute("vacante", vacante);
        return "formVacante";
    }

    @GetMapping("/delete/{id}")
    public String deleteVacante(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            vacanteService.deleteById(id);
            redirectAttributes.addFlashAttribute("msg", "La vacante fue eliminada exitosamente.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("mensaje_error", errorMessage);
        }
        return "redirect:/vacantes/index";
    }

    @PostMapping("/save")
    public String saveFormVacante(Vacante vacante, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @RequestParam("archivoImagen") MultipartFile multipartFile, SessionStatus status){
        if(bindingResult.hasErrors()){
            return "formVacante";
        }
        if(!multipartFile.isEmpty()) {
            String nombre_archivo = Utileria.saveFile(multipartFile, rutasAbsolutas.AbsoluteImage());
            if (nombre_archivo != null) {
                vacante.setImagen(nombre_archivo);
            }
        }
        if(vacante.getId() == null){
            redirectAttributes.addFlashAttribute("msg", "Registro Guardado Exitosamente");
        }else{
            redirectAttributes.addFlashAttribute("msg", "Vacante editada exitosamente.");
        }
        vacanteService.save(vacante);
        status.setComplete();
        return "redirect:/vacantes/index";
    }
}
