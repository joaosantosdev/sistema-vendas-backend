package br.com.ourmind.sistemavendas.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ourmind.sistemavendas.domains.enums.Profile;
import br.com.ourmind.sistemavendas.domains.enums.TypeClient;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(unique=true)
	private String email;

	private String cpfCnpj;

	private Integer typeClient;

	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> adresses = new ArrayList<Address>();

	@ElementCollection
	@CollectionTable(name = "phone")
	private Set<String> phones = new HashSet<String>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profile")
	private Set<Integer> profiles = new HashSet<Integer>();

	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Order> orders = new ArrayList<Order>();

	public Client() {
		this.addProfile(Profile.CLIENT);
	}

	public Client(Integer id, String name, String email, String cpfCnpj, TypeClient typeClient, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.typeClient = typeClient == null ? null : typeClient.getId();
		this.password = password;
		this.addProfile(Profile.CLIENT);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public TypeClient getTypeClient() {
		return TypeClient.toEnum(this.typeClient);
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient.getId();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Profile> getProfiles(){
		return this.profiles.stream().map(p-> Profile.toEnum(p)).collect(Collectors.toSet());
	}
	
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		this.profiles.add(profile.getId());
	}
	
	public List<Address> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
