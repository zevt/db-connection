package com.zeroexception.mongoconnection.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zeroexception.mongoconnection.model.Book;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class BookServiceImplTest {

  @Autowired
  private BookService bookService;


  @Test
  public void testReadWrite() {
    this.bookService.deleteAll();
    Book book = new Book().setTitle("Love")
        .setAuthor("Mr John")
        .setPublisherId("Oreilly");
    this.bookService.save(book);
    book = new Book().setTitle("Adventure of Tom Sawyer")
        .setAuthor("Mark Twain")
        .setPublisherId("Unknown");
    this.bookService.save(book);

    book = this.bookService.findWithTitle("Love");
    assertEquals(book.getAuthor(), "Mr John");
    List<Book> books = this.bookService.findAllByAuthor("Mark Twain");
    assertEquals(1, books.size());
    assertEquals("Adventure of Tom Sawyer", books.get(0).getTitle());

  }
}