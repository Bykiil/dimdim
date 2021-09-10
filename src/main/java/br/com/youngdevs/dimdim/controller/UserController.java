package br.com.youngdevs.dimdim.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.youngdevs.dimdim.model.User;
import br.com.youngdevs.dimdim.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repository;
	
	
	@GetMapping("/user")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<User> users = repository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	
	@GetMapping("/user/new")
	public String create(User user) {
		return "user-form";
	}
	
	@PostMapping("/user")
	public String save(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user-form";
		}
		
		repository.save(user);
		return "redirect:/user";
	}
	
	@PostMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id){
		repository.deleteById(id);
		return "redirect:/user";
	}
	
	@PostMapping("/user/edit") 
	public String edit(@Valid User user, BindingResult result){
		Optional<User> userFromDb = repository.findById(user.getId());
	    
		if(result.hasErrors()) {
			return "user-form-edit";
		}
		
		if(userFromDb.isPresent()) {
			User userDb = userFromDb.get();
			userDb.setEmail(user.getEmail());
			userDb.setNome(user.getNome());
			userDb.setSenha(user.getSenha());
		    repository.save(userDb);
		}
		
		return "redirect:/user";
	}
	
	
	@GetMapping("/user/{id}")
	public ModelAndView show(@PathVariable Long id, User user){
		ModelAndView modelAndView = new ModelAndView("user-form-edit");
		Optional<User> user1 = repository.findById(id);
		if(user1.isPresent()) {
			modelAndView.addObject((User)user1.get());
		}
		
		return modelAndView;
	}
	
}
