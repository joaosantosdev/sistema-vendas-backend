package br.com.ourmind.cursomc.domains.enums;

public enum TypeClient {
	
	LEGAL_PERSON(1),
	NATURAL_PERSON(2);
	
	private Integer id;

	private TypeClient(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}	
	
	public static TypeClient toEnum(Integer id) {
		if(id == null)
			return null;
		
		for(TypeClient tc : TypeClient.values()) {
			if(tc.getId().equals(id)) {
				return tc;
			}
		}
		
		throw new IllegalArgumentException("Id inválido");
	}
	

}
