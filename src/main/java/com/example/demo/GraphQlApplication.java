package com.example.demo;

import com.example.demo.config.DB;
import com.example.demo.model.Merchant;
import com.example.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GraphQlApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GraphQlApplication.class, args);
		init(context);
	}

	private static void init(ConfigurableApplicationContext context) {
		DB db = context.getBean(DB.class);

		User user1 = new User();
		user1.setId(1L);
		user1.setName("Alice");
		user1.setEmail("alice@mail.com");

		User user2 = new User();
		user2.setId(2L);
		user2.setName("Bob");
		user2.setEmail("bob@mail.com");

		Merchant merchant1 = new Merchant();
		merchant1.setId(1L);
		merchant1.setName("John");
		merchant1.setEmail("john@email.com");

		Merchant merchant2 = new Merchant();
		merchant2.setId(2L);
		merchant2.setName("Doe");
		merchant2.setEmail("doe@mail.com");

		merchant1.setUser(user1);
		merchant2.setUser(user2);

		user1.setMerchant(merchant2);
		user2.setMerchant(merchant1);

		db.users.add(user1);
		db.users.add(user2);

		db.merchants.add(merchant1);
		db.merchants.add(merchant2);
	}
}
