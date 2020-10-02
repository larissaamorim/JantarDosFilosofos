package jantarfilosofos;

public enum Estado {

	PENSANDO("PENSANDO"), FAMINTO("FAMINTO"), COMENDO("COMENDO");

	private String descricao;

	Estado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
