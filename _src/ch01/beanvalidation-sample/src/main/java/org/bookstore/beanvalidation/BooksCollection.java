package org.bookstore.beanvalidation;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class stores the books
 * @author Bhakti Mehta
 */
@Singleton

public class BooksCollection {

    private static final HashMap<String,Book> books = new HashMap<String,Book>();

    public static void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public static int getSize() {
           return  books.size();
    }

    public static Book deleteBook(String isbn) {
        return books.remove(isbn);
    }


    public static ArrayList<Book> getBooks() {
        return new ArrayList<Book>(books.values());
    }

    public static Book getBook(String isbn) {
        return books.get(isbn);
    }

    public BooksCollection() {
          // initial content
          addBook( new Book("foo","782345689"));

      }

}