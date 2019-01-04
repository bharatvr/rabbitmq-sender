package com.rabbitmq.component;

import java.io.NotSerializableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.models.BookDetailDTO;
import com.rabbitmq.utils.Constant;
import com.rabbitmq.utils.ValueResult;

/**
 * @author bharat
 * @since 1.0.0
 */
@Component
public class RabbitmqMessageSender {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public ValueResult<String> postMessage(BookDetailDTO bookDetailDTO) throws NotSerializableException, Exception {

		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = mapperObj.writeValueAsString(bookDetailDTO);
        
		logger.info("Received data as JSON : "+jsonStr);
        
		byte[] data = SerializationUtils.serialize(jsonStr);
		
		logger.info("Serialized Data : "+data);
		
		rabbitTemplate.convertAndSend(Constant.SINGH_BOOK_PUBLISHER_EX, Constant.SINGH_BOOK_PUBLISHER_RK, data);

		return new ValueResult<>(Constant.SUCCESS_MESSAGE);

	}

}
