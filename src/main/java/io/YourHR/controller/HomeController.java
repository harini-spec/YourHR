package io.YourHR.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.YourHR.Entity.UserEntity;
import io.YourHR.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
//	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	
	@PostMapping("/saveUser")
	public String fileUpload(@RequestParam("file") MultipartFile file, Model model, @ModelAttribute UserEntity user) throws IOException {
		String fileName =  file.getOriginalFilename();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		user.setResume(fileName);
		user.setContent(file.getBytes());
		user.setSize(file.getSize());	
		userRepository.save(user);
		return "redirect:/signup";
		}
	
}
