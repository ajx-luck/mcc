package com.mcc;

import com.mcc.domain.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinApplicationTests {
	@Autowired
	HelloService helloService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSayHello(){
		assert (helloService.sayHello().equals("Helloworld"));
	}

}
