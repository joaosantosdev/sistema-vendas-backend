package br.com.ourmind.sistemavendas.domains.enums;

public enum StatePayment {
	PENDENT(1, "Pendente"),
	FINISHED(2, "Finalizado"),
	CANCELED(3, "Cancelado");
	
	private Integer id;
	private String description;

	private StatePayment(Integer id, String description) {
		this.id = id;
		this.description = description;
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
	
	public String getDescription() {
		return this.description;
	}
}
