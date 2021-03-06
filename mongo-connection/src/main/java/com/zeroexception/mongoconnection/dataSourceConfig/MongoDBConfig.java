package com.zeroexception.mongoconnection.dataSourceConfig;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

  private String mongoUri;
  private String dbName;

  public MongoDBConfig(@Value("${spring.data.mongodb.uri}") String mongoUri,
      @Value("${spring.data.mongodb.database}") String dbName) {
    this.mongoUri = mongoUri;
    this.dbName = dbName;
  }

  @Bean
  public MongoClient mongoClient() {
    MongoClientURI clientURI = new MongoClientURI(this.mongoUri);
    return new MongoClient(clientURI);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    MongoTemplate template =  new MongoTemplate(mongoClient(), this.dbName);
    template.setReadPreference(ReadPreference.secondaryPreferred());
    template.setWriteConcern(WriteConcern.MAJORITY);
    return template;
  }
}
