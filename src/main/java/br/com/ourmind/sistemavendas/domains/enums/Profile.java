package br.com.ourmind.sistemavendas.domains.enums;

public enum Profile {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private Integer id;
	private String description;
	
	private Profile(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static Profile toEnum(Integer id) {
		if(id == null)
			return null;
		
		for(Profile tc : Profile.values()) {
			if(tc.getId().equals(id)) {
				return tc;
			}
		}
		
		throw new IllegalArgumentException("Id inválido");
	}
}
