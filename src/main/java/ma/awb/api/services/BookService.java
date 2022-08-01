package ma.awb.api.services;


import ma.awb.api.persistence.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book findById(Integer id);

    Book createNewBook(Book book);

    Book updateBook(Book book, Integer id);

    void deleteBook(Integer id);

}
