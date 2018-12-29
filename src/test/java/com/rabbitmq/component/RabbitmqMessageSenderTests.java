package com.rabbitmq.component;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import com.rabbitmq.Models.BookDetailDTO;
import com.rabbitmq.utils.Constant;

/**
 * @author bharat
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqMessageSender.class)
public class RabbitmqMessageSenderTests {

	@MockBean
	RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitmqMessageSender rabbitmqMessageSender;

	@Test
	public void testSendMessage() throws Exception {

		String args = null;

		rabbitmqMessageSender.run(args);

		BookDetailDTO bookDetailDTO = new BookDetailDTO();
		bookDetailDTO.setId(123456);
		bookDetailDTO.setAuthorName("B Singh");
		bookDetailDTO.setGenre("RabbitMQ");
		bookDetailDTO.setTitle("RabbitMQ in Action");
		bookDetailDTO.setPrice(new Double("20"));
		bookDetailDTO.setYear(2019);

		byte[] data = SerializationUtils.serialize(bookDetailDTO);

		verify(rabbitTemplate, times(2)).convertAndSend(Constant.SINGH_BOOK_PUBLISHER_EX,
				Constant.SINGH_BOOK_PUBLISHER_RK, data);

	}

}
