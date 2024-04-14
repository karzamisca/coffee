package com.shop.coffee;

import com.shop.coffee.controller.CoffeeController;
import com.shop.coffee.model.Coffee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoffeeApplicationTests {

	@Autowired
	private CoffeeController coffeeController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		// Check if the application context loads successfully
	}

	@Test
	void testGetAllCoffees() {
		// Test if the getAllCoffees method returns a non-empty list
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/coffees", List.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		List<Coffee> coffees = responseEntity.getBody();
		assertNotNull(coffees);
		Assertions.assertTrue(!coffees.isEmpty()); // Ensure the list is not empty
	}
}
