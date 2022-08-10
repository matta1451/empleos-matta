package com.example.aplicacionempleos.controllers;

import com.example.aplicacionempleos.models.entity.Usuario;
import com.example.aplicacionempleos.models.entity.Vacante;
import com.example.aplicacionempleos.models.repository.PerfilRepository;
import com.example.aplicacionempleos.services.interfaces.ICategoriasService;
import com.example.aplicacionempleos.services.interfaces.IUsuariosService;
import com.example.aplicacionempleos.services.interfaces.IVacanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("usuario")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private IVacanteService vacanteService;
    @Autowired
    private ICategoriasService categoriasService;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private IUsuariosService usuariosService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/home", "/"})
    public String showHome(){
        return "home";
    }

    @GetMapping("/search")
    public String showResultsFind(Vacante vacante, Model model){
        vacante.reset();
        //Este objeto cambia la comparación dentro del where al operador like, por ejemplo: where descripcion = ? -> where descripcion like '%?%'
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Vacante> example = Example.of(vacante, matcher);
        List<Vacante> list = vacanteService.findByExample(example);
        model.addAttribute("vacantes", list);
        return "home";
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/detalle/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model){
        model.addAttribute("oferta", vacanteService.findById(id));
        return "detalle";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "formRegistro";
    }

    @GetMapping("/encriptar/{texto}")
    @ResponseBody
    public String encriptacion(@PathVariable("texto") String texto){
        return "El texto " + texto + " encriptado es: " + passwordEncoder.encode(texto);
    }

    @PostMapping("/save")
    public String saveUser(Usuario usuario, RedirectAttributes redirectAttributes, SessionStatus status){
        String password = usuario.getPassword();
        String encriptado = passwordEncoder.encode(password);
        usuario.setPassword(encriptado);
        usuario.savePerfil(perfilRepository.findFirstByPerfil("USUARIO"));
        usuario.setFechaRegistro(new Date());
        usuario.setEstatus((byte) 1);
        usuariosService.save(usuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario Registrado Correctamente");
        status.setComplete();
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String showLogin(@RequestParam(value ="error", required = false) boolean error, Authentication authentication,
                            @RequestParam(value = "logout", required = false) boolean logout, Model model,
                            RedirectAttributes redirectAttributes){
        if(authentication != null){
            redirectAttributes.addFlashAttribute("sesion_iniciada", "Ya existe una sesión registrada en la página.");
            return "redirect:/";
        }
        if(error){
            model.addAttribute("login_error", "Ha ocurrido un error al intentar ingresar con "
                    .concat("sus credenciales. Puede que su cuenta esté inhabilitada o que las credenciales ingresadas ")
                    .concat("hayan sido incorrectas. Por favor, verifique el estado de su cuenta."));
        }
        if(logout){
            model.addAttribute("logout_success", "Sesión finalizada exitosamente. ;).");
        }
        return "formLogin";
    }

    @ModelAttribute
    public void vacantesDestacadas(Model model){
        model.addAttribute("categorias", categoriasService.findAll());
        model.addAttribute("vacantes", vacanteService.findByDestacadas());
        Vacante vacante = new Vacante();
        model.addAttribute("vacante_search", vacante);
    }
}
