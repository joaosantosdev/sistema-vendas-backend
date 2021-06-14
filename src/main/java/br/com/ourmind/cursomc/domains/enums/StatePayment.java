package br.com.ourmind.cursomc.domains.enums;

public enum StatePayment {
	PENDENT(1),
	FINISHED(2),
	CANCELED(3);
	
	private Integer id;

	private StatePayment(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}	
	
	public static StatePayment toEnum(Integer id) {
		if(id == null)
			return null;
		
		for(StatePayment tc : StatePayment.values()) {
			if(tc.getId().equals(id)) {
				return tc;
			}
		}
		
		throw new IllegalArgumentException("Id inválido");
	}
}
