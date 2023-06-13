package com.rodrigo.tvShow.services;

import com.rodrigo.tvShow.models.User;
import com.rodrigo.tvShow.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // encontrar un usuario por su email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // registrar el usuario y hacer Hash a su password
    public void registerUser(User user, BindingResult bindingResult) {
        User userRegistrado = findByEmail(user.getEmail());
        if (userRegistrado != null) {
            bindingResult.rejectValue("email", "Matches", "¡El correo ya existe!");
        } else if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            bindingResult.rejectValue("password", "Matches", "Las contraseñas deben ser idénticas");
        } else {
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashed);
            userRepository.save(user);
        }
    }

    // encontrar un usuario por su id
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // autenticar usuario
    public boolean authenticateUser(String LoginEmail, String Loginpassword, BindingResult bindingResult) {
        // primero encontrar el usuario por su email
        User user = findByEmail(LoginEmail);
        // si no lo podemos encontrar por su email, retornamos false
        if (user == null) {
            bindingResult.rejectValue("emailLogin", "Matches", "Email no válido!");
            return false;
        } else {
            // si el password coincide devolvemos true, sino, devolvemos false
            if (!BCrypt.checkpw(Loginpassword, user.getPassword())) {
                bindingResult.rejectValue("passwordLogin", "Matches", "Contraseña incorrecta");
                return false;
            }
            return true;
        }
    }
}
