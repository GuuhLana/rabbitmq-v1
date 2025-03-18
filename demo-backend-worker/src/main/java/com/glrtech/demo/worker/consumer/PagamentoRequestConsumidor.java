package com.glrtech.demo.worker.consumer;

import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.glrtech.demo.worker.producer.PagamentoErroProdutor;
import com.glrtech.demo.worker.producer.PagamentoSucessoProdutor;

@Component
public class PagamentoRequestConsumidor {
	@Autowired
	private PagamentoErroProdutor erroProdutor;

	@Autowired
	private PagamentoSucessoProdutor sucessoProdutor;

	@RabbitListener(queues = { "pagamento-request-queue" })
	public void receberMensagem(@Payload Message message) {
		System.out.println(message);
		
		boolean aleatorizador = new Random().nextBoolean();
		
		if (aleatorizador) {
			sucessoProdutor.gerarResposta("Mensagem de sucesso Pagamento " + message);
		} else {
			erroProdutor.gerarResposta("Erro no pagamento " + message);
		}
	}
}
