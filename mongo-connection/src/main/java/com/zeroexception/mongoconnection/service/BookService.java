package com.zeroexception.mongoconnection.service;

import com.zeroexception.mongoconnection.model.Book;
import java.util.List;

/**
 * @author Viet Quoc Tran vt on 6/5/18. www.zeroexception.com
 */

public interface BookService {


  void save(Book book);

  Book findWithTitle(String title);

  List<Book> findAllByAuthor(String author);

  void deleteAll();
}
