package br.com.fiap.selfcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.selfcare.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Long countById(Long id);
	
}
