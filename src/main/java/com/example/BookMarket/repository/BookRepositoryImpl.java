package com.example.BookMarket.repository;

import com.example.BookMarket.domain.Book;
import com.example.BookMarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book("ISBN1234", "C# 교과서", 30000);
        book1.setAuthor("박용준");
        book1.setDescription("이 책은 ~~ 목적이다.");
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2020/05/29");
        book1.setFileName("ISBN1234.jpg");

        Book book2 = new Book("ISBN1235", "Node.js 교과서", 360000);
        book2.setAuthor("조현영");
        book2.setDescription("이 책은 ~~ 적용할 수 있다.");
        book2.setPublisher("길벗");
        book2.setCategory("IT전문서");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2020/07/25");
        book2.setFileName("ISBN1235.jpg");

        Book book3 = new Book("ISBN1236", "어도비 XD CC 2020", 250000);
        book3.setAuthor("김두한");
        book3.setDescription("어도비 XD 프로그램을 ~~ 학습합니다.");
        book3.setPublisher("길벗");
        book3.setCategory("IT교육교재");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate("2019/05/29");
        book3.setFileName("ISBN1236.jpg");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);

    }

    public Book getBookById(String bookId) {
        Book bookInfo = null;
        for(int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if(book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                bookInfo = book;
                break;
            }
        }
        if(bookInfo == null) {
//            throw new IllegalArgumentException("도서ID가 " + bookId + "인 해당 도서를 찾을 수 없습니다.");
            throw new BookIdException(bookId);
        }
        return bookInfo;
    }

    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCatefory = new ArrayList<Book>();
        for(int i = 0; i < listOfBooks.size(); i++) {
            Book book = listOfBooks.get(i);
            if(category.equalsIgnoreCase(book.getCategory())) {
                booksByCatefory.add(book);
            }
        }
        return booksByCatefory;
    }

    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> booksByFilter = filter.keySet();
        if(booksByFilter.contains("publisher")) {
            for(int j=0; j<filter.get("publisher").size(); j++) {
                String pubisherName = filter.get("publisher").get(j);
                for (int i =0; i < listOfBooks.size(); i++) {
                    Book book = listOfBooks.get(i);
                    if (pubisherName.equalsIgnoreCase(book.getPublisher()))
                        booksByPublisher.add(book);

                }
            }
        }
        if(booksByFilter.contains("category")) {
            for(int i=0; i <filter.get("category").size(); i++) {
                String category = filter.get("category").get(i);
                List<Book> list = getBookListByCategory(category);
                booksByCategory.addAll(list);
            }
        }
        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }

    @Override
    public List<Book> getAllBookList() {
        // TODO Auto-generated method stub
        return listOfBooks;
    }
}
