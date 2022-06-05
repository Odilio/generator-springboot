package com.mycompany.myservice.adapters.outbound;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mycompany.myservice.adapters.entities.Auth;
import com.mycompany.myservice.ports.out.UserRepositoryPort;

@Repository
public class AuthRepositoryAdapter implements UserRepositoryPort {
	public Optional<Auth> findByUsername(String username) {
	return null;
}		

  public Boolean existsByUsername(String username) {
	return null;
}

  public Boolean existsByEmail(String email) {
	return null;
}

}
