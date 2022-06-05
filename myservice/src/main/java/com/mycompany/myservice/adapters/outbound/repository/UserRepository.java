package com.mycompany.myservice.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myservice.adapters.entities.Auth;

@Repository
public interface UserRepository extends JpaRepository<Auth, Long> {
  Optional<Auth> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
