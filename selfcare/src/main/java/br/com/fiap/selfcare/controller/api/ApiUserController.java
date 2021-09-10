package br.com.fiap.selfcare.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.selfcare.model.User;
import br.com.fiap.selfcare.repository.UserRepository;

@RestController
public class ApiUserController {
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/api/users")
	public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
		repository.save(user);
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping("/api/users/{id}")
	public ResponseEntity<User> read(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@PutMapping("/api/users/{id}")
	public ResponseEntity<User> update(@RequestBody User userUpdated, @PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		User user = optional.get();
		user.setName(userUpdated.getName());
		user.setAge(userUpdated.getAge());
		user.setWeight(userUpdated.getWeight());
		user.setHeight(userUpdated.getHeight());
		user.setComorbidity(userUpdated.isComorbidity());
		user.setMedication(userUpdated.isMedication());
		user.setActivity(userUpdated.getActivity());
		repository.save(user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/api/users/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/users")
	public List<User> list() {
		return repository.findAll();
	}
	
}
