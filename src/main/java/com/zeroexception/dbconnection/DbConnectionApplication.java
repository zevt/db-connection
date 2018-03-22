package com.zeroexception.dbconnection;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		node.put("time stamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:MM:ss")));
		this.mongoTemplate.save(node, "test");
	}
}
