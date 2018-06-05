package com.zeroexception.dbconnection;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-awsdev.properties"})
public class MongoDBConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoConnection() {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("message","test");
        this.mongoTemplate.save(node, "test");
    }

}
