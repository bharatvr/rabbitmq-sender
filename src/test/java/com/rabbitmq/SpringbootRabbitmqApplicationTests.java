package com.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabbitmq.component.RabbitmqMessageSender;

/**
 * @author bharat
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
public class SpringbootRabbitmqApplicationTests {

	@MockBean
	RabbitmqMessageSender rabbitmqMessageSender;

	@Test
	public void contextLoads() {
	}

}
