package com.example.BookMarket.repository;

import com.example.BookMarket.domain.Book;
import com.example.BookMarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        book1.setBookId("ISBN1234");
        book1.setName ("자바스크립트 입문");
        book1.setUnitPrice(new BigDecimal(30000));
        book1.setAuthor("qwer");
        book1.setDescription(
                "123124");
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2024/02/20");
        book1.setFileName("ISBN1234.jpg");

        Book book2 = new Book();
        book2.setBookId("ISBN1235");
        book2.setName ("파이썬의 정석");
        book2.setUnitPrice(new BigDecimal(29800));
        book2.setAuthor("wert");
        book2.setDescription(
                "123");
        book2.setPublisher("길벗");
        book2.setCategory("IT교육교재");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2023/01/10");
        book2.setFileName("ISBN1235.jpg");

        Book book3 = new Book();
        book3.setBookId("ISBN1236");
        book3.setName ("안드로이드 프로그래밍");
        book3.setUnitPrice(new BigDecimal(25000));
        book3.setAuthor("erty");
        book3.setDescription(
                "4567");
        book3.setPublisher("길벗");
        book3.setCategory("IT교육교재");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate("2023/06/30");
        book3.setFileName("ISBN1236.jpg");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);

    }

    public List<Book> getAllBookList() {
        return listOfBooks;
    }


    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for(int i =0 ; i<listOfBooks.size() ; i++) {
            Book book = listOfBooks.get(i);
            if(category.equalsIgnoreCase(book.getCategory()))
                booksByCategory.add(book);
        }
        return booksByCategory;
    }

    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();

        Set<String> booksByFilter = filter.keySet();

        if (booksByFilter.contains("publisher")) {
            for (int j = 0; j < filter.get("publisher").size(); j++) {
                String pubisherName = filter.get("publisher").get(j);
                for (int i = 0; i < listOfBooks.size(); i++) {
                    Book book = listOfBooks.get(i);

                    if (pubisherName.equalsIgnoreCase(book.getPublisher()))
                        booksByPublisher.add(book);
                }
            }
        }

        if (booksByFilter.contains("category")) {
            for (int i = 0; i < filter.get("category").size(); i++) {
                String category = filter.get("category").get(i);
                List<Book> list = getBookListByCategory(category);
                booksByCategory.addAll(list);
            }
        }

        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    public Book getBookById(String bookId) {
        Book bookInfo = null;
        for(int i =0 ;i<listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if (book!=null && book.getBookId()!=null && book.getBookId().equals(bookId)){
                bookInfo = book;
                break;
            }
        }
        if(bookInfo == null)
            // throw new IllegalArgumentException("도서ID가 "+bookId + "인 해당 도서를 찾을 수 없습니다.");
            throw new BookIdException(bookId);
        return bookInfo;
    }

    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }

}
