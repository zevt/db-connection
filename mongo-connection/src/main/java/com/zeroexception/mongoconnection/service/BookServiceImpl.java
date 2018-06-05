package com.zeroexception.mongoconnection.service;

import com.zeroexception.mongoconnection.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

  private static final String BOOK_COLLECTION = "books";

  private MongoTemplate mongoTemplate;

  @Autowired
  public BookServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void save(Book book) {
    this.mongoTemplate.save(book, BOOK_COLLECTION);
  }

  @Override
  public Book findWithTitle(String title) {

    Query query = Query.query(Criteria.where("title").is(title)).limit(1);
    List<Book> bookList = this.mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
    return bookList.size() > 0 ? bookList.get(0) : null;
  }

  @Override
  public List<Book> findAllByAuthor(String author) {
    Query query = Query.query(Criteria.where("author").is(author));
    return this.mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
  }

  @Override
  public void deleteAll() {
    this.mongoTemplate.dropCollection(BOOK_COLLECTION);
  }
}
