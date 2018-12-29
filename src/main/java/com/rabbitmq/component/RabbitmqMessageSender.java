package com.rabbitmq.component;

import java.io.NotSerializableException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import com.rabbitmq.utils.Constant;

import com.rabbitmq.Models.BookDetailDTO;

/**
 * @author bharat
 * @since 1.0
 */
@Component
public class RabbitmqMessageSender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void run(String... args) throws NotSerializableException, Exception {

		BookDetailDTO bookDetailDTO = new BookDetailDTO();
		bookDetailDTO.setId(123456);
		bookDetailDTO.setAuthorName("B Singh");
		bookDetailDTO.setGenre("RabbitMQ");
		bookDetailDTO.setTitle("RabbitMQ in Action");
		bookDetailDTO.setPrice(new Double("20"));
		bookDetailDTO.setYear(2019);

		byte[] data = SerializationUtils.serialize(bookDetailDTO);
		// TODO : Need to convert DTO to JSON, sending java object is not ideal approach

		System.out.println("DTO Data object has been successfully serialize before sending to rabbitmq");

		rabbitTemplate.convertAndSend(Constant.SINGH_BOOK_PUBLISHER_EX, Constant.SINGH_BOOK_PUBLISHER_RK, data);

		System.out.println("Message has been post successfully to rabbitmq");

	}

}
