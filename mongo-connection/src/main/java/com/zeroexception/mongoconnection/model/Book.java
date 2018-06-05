package com.zeroexception.mongoconnection.model;

import java.math.BigInteger;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("Book")
public class Book {

  @Id @Getter private BigInteger id;
  @Getter private String title;
  @Getter private String author;
  @Getter private String publisherId;

  public Book setId(BigInteger id) {
    this.id = id;
    return this;
  }

  public Book setTitle(String title) {
    this.title = title;
    return this;
  }

  public Book setAuthor(String author) {
    this.author = author;
    return this;
  }

  public Book setPublisherId(String publisherId) {
    this.publisherId = publisherId;
    return this;
  }
}
