package com.example.aplicacionempleos.controllers;

import com.example.aplicacionempleos.models.entity.Categoria;
import com.example.aplicacionempleos.services.interfaces.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
@SessionAttributes("categoria")
public class CategoriaController {

    @Autowired
    private ICategoriasService categoriasService;

    //@Value("${error.message}")
    private String errorMessage = "Ha ocurrido un error al intentar eliminar este registro. La causa de esto podria ser, que este registro \\\n" +
            "  este vinculado con alguno otro y por ello al intentar eliminarlo, se estaria eliminando todos aquellos que esten \\\n" +
            "  enlazados a este.";

    @GetMapping("/index")
    public String showCategory(Model model, Pageable pageable){
        Page<Categoria> paginas = categoriasService.findAllPage(pageable);
        model.addAttribute("categorias", paginas);
        return "listCategorias";
    }

    @GetMapping("/form")
    public String showFormCategory(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "formCategoria";
    }

    @GetMapping("/update/{id}")
    public String updateFormCategory(@PathVariable("id") Long id, Model model){
        Categoria categoria = categoriasService.findById(id);
        model.addAttribute("categoria", categoria);
        return "formCategoria";
    }

    @GetMapping("/delete/{id}")
    public String deleteFormCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            categoriasService.deleteById(id);
            redirectAttributes.addFlashAttribute("msg", "Registro eliminado exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("mensaje_error", errorMessage);
        }
        return "redirect:/categorias/index";
    }

    @PostMapping("/save")
    public String saveCategory(Categoria categoria, RedirectAttributes redirectAttributes, SessionStatus status){
        if(categoria.getId() == null){
            redirectAttributes.addFlashAttribute("msg", "Registro Guardado Exitosamente");
        }else{
            redirectAttributes.addFlashAttribute("msg", "Registro Actualizado Exitosamente");
        }
        categoriasService.save(categoria);
        status.setComplete();
        return "redirect:/categorias/index";
    }
}
