package com.rabbitmq.controller;

import java.io.NotSerializableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.component.RabbitmqMessageSender;
import com.rabbitmq.models.BookDetailDTO;
import com.rabbitmq.utils.ValueResult;

/**
 * @author bharat
 * @since 1.0.0
 *
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	RabbitmqMessageSender rabbitmqMessageSender;

	/**
	 * Check if this service is healthy
	 * 
	 * @return HttpStatus for corresponding case
	 */
	@GetMapping(value = "/healthcheck")
	public ValueResult<String> healthCheck() {
		return new ValueResult<>("Great!, service is up and running");
	}

	@PostMapping("/publish_book")
	public ValueResult<String> publishBook(@RequestBody BookDetailDTO bookDetails) throws NotSerializableException, Exception {
		return rabbitmqMessageSender.postMessage(bookDetails);
	}

}