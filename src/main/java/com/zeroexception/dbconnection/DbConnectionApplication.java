package com.zeroexception.dbconnection;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DbConnectionApplication implements CommandLineRunner {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DbConnectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("message","test");
		this.mongoTemplate.save(node, "test");
	}
}
