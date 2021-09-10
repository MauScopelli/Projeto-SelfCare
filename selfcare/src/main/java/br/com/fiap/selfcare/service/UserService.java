package br.com.fiap.selfcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.selfcare.exception.UserNotFoundException;
import br.com.fiap.selfcare.model.User;
import br.com.fiap.selfcare.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> listAll() {
		return repository.findAll();
	}

	public void save(User user) {
		repository.save(user);
	}
	
	public User getById(Long id) throws UserNotFoundException {
		Optional<User> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		throw new UserNotFoundException("Could not find any users with ID " + id);
	}
	
	public void deleteById(Long id) throws UserNotFoundException {
		Long count = repository.countById(id);
		if (count == null || count == 0) {
			throw new UserNotFoundException("Could not find any users with ID " + id);
		}
		repository.deleteById(id);
	}
	
}
