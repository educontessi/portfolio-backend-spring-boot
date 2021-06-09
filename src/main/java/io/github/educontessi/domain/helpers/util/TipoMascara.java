package io.github.educontessi.domain.helpers.util;

import io.github.educontessi.domain.enums.TipoPessoa;

/**
 * Classe usada para mostrar qual o tipo de máscara usar
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public enum TipoMascara {

	CPF("###.###.###-##"), CNPJ("##.###.###/####-##"), CEP("#####-###");

	private String mascara;

	private TipoMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getMascara() {
		return mascara;
	}

	public static TipoMascara getTipoMascara(TipoPessoa tipoPessoa) {
		return tipoPessoa.equals(TipoPessoa.FISICA) ? TipoMascara.CPF : TipoMascara.CNPJ;
	}

}
