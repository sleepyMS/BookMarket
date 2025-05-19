package com.example.BookMarket.domain;


import com.example.BookMarket.validator.BookId;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class Book {
    @BookId
    @Pattern(regexp="ISBN[1-9]+", message="{Pattern.book.bookId}")
    private String bookId;	// 도서 ID

    @Size(min=4, max=50, message = "{Size.book.name}")
    private String name;	// 도서명

    @Min(value=0, message="{Min.book.unitPrice}")
    @Digits(integer=8, fraction=2, message="{Digits.book.unitPrice}")
    @NotNull(message="{NotNull.book.unitPrice}")
    private int unitPrice;	// 가격
    private String author;	// 저자
    private String description;	// 설명
    private String publisher;	// 출판사
    private String category;	// 분류
    private long unitsInStock;	// 재고 수
    private String releaseDate;	// 출판일(월/년)
    private String condition;	// 신규 도서 또는 중고 도서 또는 전자책
    private String fileName;    // 도서 이미지 파일
    private MultipartFile bookImage;    // 도서 이미지

    public Book(String bookId, String name, int unitPrice) {
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
    }
}
