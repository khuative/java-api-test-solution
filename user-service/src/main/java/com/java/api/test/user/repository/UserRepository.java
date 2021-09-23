package com.java.api.test.user.repository;

import com.java.api.test.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    Page<User> findByFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String firstNameQuery, String lastNameQuery, Pageable pageable);
}
