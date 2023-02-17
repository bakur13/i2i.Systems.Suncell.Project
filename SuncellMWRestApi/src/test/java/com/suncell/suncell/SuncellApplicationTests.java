package com.suncell.suncell;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.suncell.rest.api.SuncellApplication;

@SpringBootTest(classes = SuncellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuncellApplicationTests {

	@Test
	void contextLoads() {
	}

}
