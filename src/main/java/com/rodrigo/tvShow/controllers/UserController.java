package com.rodrigo.tvShow.controllers;

import com.rodrigo.tvShow.models.LoginUser;
import com.rodrigo.tvShow.models.User;
import com.rodrigo.tvShow.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String form(Model model, HttpSession httpSession) {
        model.addAttribute("user", new User());
        model.addAttribute("loginUser", new LoginUser());
        System.out.println(httpSession.getAttribute("userId") + " atributo ");
        return "form";
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", new LoginUser());
            return "form";
        } else {
            userService.registerUser(user, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("loginUser", new LoginUser());
                return "form";
            } else {
                redirectAttributes.addFlashAttribute("success", "Usuario registrado con éxito!");
                return "redirect:/";
            }
        }
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            return "form";
        }
        if (userService.authenticateUser(loginUser.getEmailLogin(), loginUser.getPasswordLogin(), bindingResult)) {
            User userLog = userService.findByEmail(loginUser.getEmailLogin());
            httpSession.setAttribute("userId", userLog.getId());
            System.out.println(httpSession.getAttribute("userId") + " atributo ");
            redirectAttributes.addFlashAttribute("login", "Usuario autenticado con exito!");
            return "redirect:/show";
        }
        model.addAttribute("user", new User());
        return "form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
        httpSession.invalidate();
        return "redirect:/";
    }
}
