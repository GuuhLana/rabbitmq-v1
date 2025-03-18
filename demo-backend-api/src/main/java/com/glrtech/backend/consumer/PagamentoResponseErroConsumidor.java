package com.glrtech.backend.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.glrtech.backend.facade.PagamentoFacade;

@Component
public class PagamentoResponseErroConsumidor {

	@Autowired 
	private PagamentoFacade pagamentoFacade;
	
	@RabbitListener(queues = {"pagamento-response-erro-queue"})
	public void receive(@Payload Message message) {
		System.out.println("Message " + message + " " + LocalDateTime.now());
		String payload = String.valueOf(message.getPayload());
		
		pagamentoFacade.erroPagamento(payload);
	}
}
