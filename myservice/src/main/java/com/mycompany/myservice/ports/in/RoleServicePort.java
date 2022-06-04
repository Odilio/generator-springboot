package com.mycompany.myservice.ports.in;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myservice.adapters.entities.ERole;
import com.mycompany.myservice.adapters.entities.Role;


public interface RoleServicePort {
  Optional<Role> findByName(ERole name);
}
