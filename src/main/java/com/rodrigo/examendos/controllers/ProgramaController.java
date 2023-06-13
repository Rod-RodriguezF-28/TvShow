package com.rodrigo.examendos.controllers;

import com.rodrigo.examendos.models.Calificacion;
import com.rodrigo.examendos.models.Programa;
import com.rodrigo.examendos.models.User;
import com.rodrigo.examendos.services.CalificacionService;
import com.rodrigo.examendos.services.ProgramaService;
import com.rodrigo.examendos.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProgramaController {

    private final UserService userService;
    private final ProgramaService programaService;
    private final CalificacionService calificacionService;

    public ProgramaController(UserService userService, ProgramaService programaService, CalificacionService calificacionService) {
        this.userService = userService;
        this.programaService = programaService;
        this.calificacionService = calificacionService;
    }

    @GetMapping("/show")
    public String show(HttpSession httpSession, Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");
        model.addAttribute("usuario", userService.findUserById(userId));
        model.addAttribute("programas", programaService.getProgramas());
        return "show";
    }

    @GetMapping("/shows/new")
    public String showNew(@ModelAttribute("programa") Programa programa, HttpSession httpSession,
                          Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userService.findUserById(userId);
        model.addAttribute("usuario", user);
        return "showNew";
    }

    @PostMapping("/shows/new")
    public String showNew(@Valid @ModelAttribute("programa") Programa programa, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, HttpSession httpSession,
                          Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");

        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findUserById(userId));
            return "showNew";
        }
        try {
            programaService.guardarPrograma(programa);
            redirectAttributes.addFlashAttribute("success", "Programa guardado");
        } catch (DataIntegrityViolationException e) {
            // Se captura la excepción cuando se viola la restricción única
            bindingResult.rejectValue("titulo", "duplicate", "El título y la red deben ser únicos");
            model.addAttribute("usuario", userService.findUserById(userId));
            return "showNew";
        }
        return "redirect:/show";
    }

    @GetMapping("/shows/{id}")
    public String showsId(@ModelAttribute("calificacion") Calificacion calificacion,
                          @PathVariable("id") Long id, HttpSession httpSession,
                          Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        Programa programa = programaService.getProgramaId(id);
        User usuario = userService.findUserById(userId);
        List<Calificacion> calificaciones = calificacionService.getCalificacionesByPrograma(programa);

        model.addAttribute("programa", programa);
        model.addAttribute("usuario", usuario);
        model.addAttribute("calificaciones", calificaciones);
        return "showId";
    }


    @PostMapping("/shows/{programaId}/{userId}")
    public String procesarFormularioCalificacion(@Valid @ModelAttribute("calificacion") Calificacion calificacion,
                                                 BindingResult result,
                                                 @PathVariable("programaId") Long programaId,
                                                 @PathVariable("userId") Long userId,
                                                 RedirectAttributes redirectAttributes,
                                                 Model model) {

        if (userId == null) {
            return "redirect:/";
        }
        Programa programa = programaService.getProgramaId(programaId);
        // Validar si existen errores de validación en el formulario
        if (result.hasErrors()) {
            model.addAttribute("programa", programaService.getProgramaId(programaId));
            model.addAttribute("usuario", userService.findUserById(userId));
            model.addAttribute("calificaciones", calificacionService.getCalificacionesByPrograma(programa));
            //model.addAttribute("bindingResult", result);
            return "showId"; // o la vista correspondiente para mostrar los errores
        }

        Double rating = calificacion.getRating();

        // Obtener el usuario y el programa
        User usuario = userService.findUserById(userId);


        // Verificar si el usuario ya ha calificado el programa
        if (calificacionService.existsByUserAndPrograma(usuario, programa)) {
            model.addAttribute("programa", programa);
            model.addAttribute("usuario", usuario);
            model.addAttribute("calificaciones", calificacionService.getCalificacionesByPrograma(programa));
            model.addAttribute("danger", "El usuario ya ha calificado este programa");
            model.addAttribute("bindingResult", result);
            return "showId"; // o la vista correspondiente para mostrar el error
        }

        // Crear la calificación
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setRating(rating);
        nuevaCalificacion.setUser(usuario);
        nuevaCalificacion.setPrograma(programa);

        // Agregar el usuario al modelo antes de devolver la vista
        model.addAttribute("usuario", usuario);
        // Guardar la calificación en la base de datos
        calificacionService.insertCalificacion(nuevaCalificacion);
        redirectAttributes.addFlashAttribute("success", "Calificacion ingresada!");
        return "redirect:/shows/" + programaId; // o la vista que corresponda después de guardar la calificación
    }

    @GetMapping("/shows/{id}/edit")
    public String editShow(@ModelAttribute("programa") Programa programa,
                           @PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes,
                           Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        Programa programa1 = programaService.getProgramaId(id);
        if (programa1 == null) {
            redirectAttributes.addFlashAttribute("danger", "Error al editar el programa");
            return "redirect:/show";
        }
        model.addAttribute("programa", programa1);
        model.addAttribute("usuario", userService.findUserById(userId));
        return "showEdit";
    }

    @PutMapping("/shows/{id}/edit")
    public String editShow(@Valid @ModelAttribute("programa")Programa programa,
                           BindingResult bindingResult,
                           @PathVariable("id")Long id,
                           RedirectAttributes redirectAttributes,
                           HttpSession httpSession, Model model) {
        Long userId = (Long) httpSession.getAttribute("userId");

        Programa programa1 = programaService.getProgramaId(id);
        if (userId == null) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", userService.findUserById(userId));
            return "showEdit";
        }
        programa.setId(programa1.getId()); // Agregar esta línea para asignar el ID al programa
        programaService.updatePrograma(programa);
        redirectAttributes.addFlashAttribute("warning", "Programa actualizado");
        return "redirect:/show";
    }

    @GetMapping("/delete/{id}")
    public String deletePrograma(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        programaService.deleteProgramaById(id);
        redirectAttributes.addFlashAttribute("danger", "Programa eliminado con exito!");
        return "redirect:/show";
    }


}
