package com.mycompany.myservice.adapters.outbound;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.myservice.adapters.entities.ERole;
import com.mycompany.myservice.adapters.entities.Role;

@Repository
public class RoleRepositoryAdapter implements JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
