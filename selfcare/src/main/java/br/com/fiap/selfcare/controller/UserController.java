package br.com.fiap.selfcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.selfcare.exception.UserNotFoundException;
import br.com.fiap.selfcare.model.User;
import br.com.fiap.selfcare.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String users(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("pageTitle", "User's");
		return "index";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("pageTitle", "New User");
		return "form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes ra) {
		service.save(user);
		ra.addFlashAttribute("message", "User has been saved succesfully!");
		return "redirect:/";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
		try {
			User user = service.getById(id);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User " + id);
			return "form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
		try {
			service.deleteById(id);
			ra.addFlashAttribute("message", "User with ID " + id + " has been deleted.");
		} catch (Exception e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/";
	}
	
}
