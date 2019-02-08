package com.zeroexception.mongoconnection;

import com.zeroexception.mongoconnection.model.Book;
import com.zeroexception.mongoconnection.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class MongoConnectionApplicationTests {

  @Autowired
  private BookService bs;

  @Test
  public void contextLoads() {
    Book book = new Book().setAuthor("Mark Twain")
        .setTitle("Adventure of Tom Sower");
    this.bs.save(book);
  }
}
