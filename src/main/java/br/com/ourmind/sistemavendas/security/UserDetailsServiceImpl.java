package br.com.ourmind.sistemavendas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.repositories.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = this.clientRepository.findByEmail(email);
		
		if(client == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}

}
