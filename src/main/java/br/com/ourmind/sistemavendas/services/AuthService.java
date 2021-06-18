package br.com.ourmind.sistemavendas.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.repositories.ClientRepository;
import br.com.ourmind.sistemavendas.services.exeptions.NotFoundResourceException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Client client = this.clientRepository.findByEmail(email);
		if(client == null)
			throw new NotFoundResourceException("Usuário não encontrado.");
		
		String newPassword = this.newPassword();
		client.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		
		this.clientRepository.save(client);
		
		this.emailService.sendNewPassword(client, newPassword);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int op = this.random.nextInt(3);
				
		if(op == 0) {
			return (char) (this.random.nextInt(10) + 48);
		}else if(op == 1) {
			return (char) (this.random.nextInt(26) + 65);
		}else {
			return (char) (this.random.nextInt(26) + 97);
		}
	}
	
}
