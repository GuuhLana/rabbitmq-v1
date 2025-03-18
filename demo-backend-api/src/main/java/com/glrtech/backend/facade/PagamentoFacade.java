package com.glrtech.backend.facade;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.glrtech.backend.dto.PagamentoDTO;
import com.glrtech.backend.producer.PagamentoRequestProducer;

@Service
public class PagamentoFacade {

	@Autowired
	private PagamentoRequestProducer producer;

	public String solicitarPagamento(PagamentoDTO request) {
		try {
			producer.integrar(request);
		} catch (JsonProcessingException e) {
			return "Ocorreu um erro ao solicitar pagamento: " + e.getMessage();
		} catch (AmqpException e) {
			return "Ocorreu um erro ao solicitar pagamento: " + e.getMessage();
		}
		return "Pagamento aguardando confirmação...";
	}

	public void erroPagamento(String payload) {
		System.err.println("====== RESPOSTA ERRO ======" + payload);
	}

	public void sucessoPagamento(String payload) {
		System.out.println("====== RESPOSTA SUCESSO ======" + payload);
	}

}
