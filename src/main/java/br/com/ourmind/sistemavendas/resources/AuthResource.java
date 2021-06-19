package br.com.ourmind.sistemavendas.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ourmind.sistemavendas.dto.EmailDTO;
import br.com.ourmind.sistemavendas.security.JWTUtil;
import br.com.ourmind.sistemavendas.security.UserSS;
import br.com.ourmind.sistemavendas.security.UserService;
import br.com.ourmind.sistemavendas.services.AuthService;


@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired 
	private AuthService authService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = this.userService.authenticated();
		String token = this.jwtUtil.generateToken(user.getUsername());
		response.addHeader("access-control-expose-headers", "Authorization");
		response.addHeader("Authorization", "Bearer "+token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO ) {
		this.authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
