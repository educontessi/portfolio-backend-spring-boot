package io.github.educontessi.helpers.util;

public enum TipoMascara {

	CPF("###.###.###-##"), CNPJ("##.###.###/####-##"), CEP("#####-###");

	private String mascara;

	private TipoMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getMascara() {
		return mascara;
	}

}
