package com.uyltos.cospace.repository;

import com.uyltos.cospace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
