package com.mycompany.myservice.ports.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myservice.adapters.entities.Auth;

public interface UserRepositoryPort {
  Optional<Auth> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
