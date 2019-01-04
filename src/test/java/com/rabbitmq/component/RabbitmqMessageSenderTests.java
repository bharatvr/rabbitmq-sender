package com.rabbitmq.component;

import static org.junit.Assert.assertEquals;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.models.BookDetailDTO;
import com.rabbitmq.utils.Constant;
import com.rabbitmq.utils.ValueResult;

/**
 * @author bharat
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqMessageSender.class)
public class RabbitmqMessageSenderTests {

	@MockBean
	RabbitTemplate rabbitTemplate;

	@Autowired
	RabbitmqMessageSender rabbitmqMessageSender;

	@Test
	public void testSendMessage() throws Exception {
		BookDetailDTO bookDetailDTO = new BookDetailDTO();
		bookDetailDTO.setId(123456);
		bookDetailDTO.setAuthorName("B Singh");
		bookDetailDTO.setGenre("RabbitMQ");
		bookDetailDTO.setTitle("RabbitMQ in Action");
		bookDetailDTO.setPrice(new Double("20"));
		bookDetailDTO.setYear(2019);

		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = mapperObj.writeValueAsString(bookDetailDTO);

		byte[] data = SerializationUtils.serialize(jsonStr);

		ValueResult<String> message = rabbitmqMessageSender.postMessage(bookDetailDTO);

		assertEquals(message.isSuccess(), true);

		verify(rabbitTemplate, times(1)).convertAndSend(Constant.SINGH_BOOK_PUBLISHER_EX,
				Constant.SINGH_BOOK_PUBLISHER_RK, data);

	}

}
