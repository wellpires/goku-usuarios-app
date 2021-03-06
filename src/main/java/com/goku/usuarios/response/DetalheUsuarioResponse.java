package com.goku.usuarios.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goku.usuarios.dto.DetalheUsuarioDTO;

public class DetalheUsuarioResponse {

	@JsonProperty("usuario-detalhe")
	private DetalheUsuarioDTO detalheUsuarioDTO;

	public DetalheUsuarioResponse(DetalheUsuarioDTO detalheUsuarioDTO) {
		this.detalheUsuarioDTO = detalheUsuarioDTO;
	}

	public DetalheUsuarioResponse() {
	}

	public DetalheUsuarioDTO getDetalheUsuarioDTO() {
		return detalheUsuarioDTO;
	}

	public void setDetalheUsuarioDTO(DetalheUsuarioDTO detalheUsuarioDTO) {
		this.detalheUsuarioDTO = detalheUsuarioDTO;
	}

}
