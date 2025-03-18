package com.glrtech.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glrtech.backend.dto.PagamentoDTO;
import com.glrtech.backend.facade.PagamentoFacade;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoClient {

	@Autowired
	private PagamentoFacade pagamentoFacade;

	@PostMapping
	public String processar(@RequestBody PagamentoDTO request) {
		return pagamentoFacade.solicitarPagamento(request);
	}
}
