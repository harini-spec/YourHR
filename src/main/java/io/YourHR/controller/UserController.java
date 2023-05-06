package io.YourHR.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.YourHR.Entity.UserEntity;
import io.YourHR.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void addCommonData(Principal p, Model model) { 
		String email = p.getName();
		UserEntity user = userRepository.findByEmail(email);
		model.addAttribute("user", user);
	}
	
	@GetMapping("/home")
	public String addNotes() {
		return "/user/home";
	}
	
	@GetMapping("/rocket")
	public String apply1() {
		return "/user/rocket";
	}
	
	@GetMapping("/arbon")
	public String apply2() {
		return "/user/arbon";
	}
	
	@GetMapping("/datasio")
	public String apply3() {
		return "/user/datasio";
	}
	
	@GetMapping("/success")
	public String applied(RedirectAttributes redirAttrs) {
		redirAttrs.addFlashAttribute("success", "Successfully Applied!");
		return "redirect:/user/home";
	}

}
