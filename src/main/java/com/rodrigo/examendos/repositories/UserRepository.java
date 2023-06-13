package com.rodrigo.examendos.repositories;

import com.rodrigo.examendos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
