package com.example.BookMarket.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class CartItem {

    private Book book;
    private int quantity;
    private BigDecimal  totalPrice;


    public CartItem(Book book) {
        super();
        this.book = book;
        this.quantity = 1;
        this.totalPrice = book.getUnitPrice();
    }

    /*public CartItem() {

    }

        public Book getBook() {
        return book;
    }
*/
    public void setBook(Book book) {
        this.book = book;
        this.updateTotalPrice();
    }

    /*	public int getQuantity() {
            return quantity;
        }
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }
    /*
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
*/
    public void updateTotalPrice() {
        totalPrice =  this.book.getUnitPrice().multiply(new BigDecimal(this.quantity));
    }

}
