package com.nikdemo.e_bookbookreadingapp.model;

public class BookModel {
    
    private String book_title,book_description;
    private double book_rating;
    private boolean isLike;
    private int book_cover;

    public BookModel(String book_title, String book_description, double book_rating, boolean isLike, int book_cover) {
        this.book_title = book_title;
        this.book_description = book_description;
        this.book_rating = book_rating;
        this.isLike = isLike;
        this.book_cover = book_cover;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public double getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(double book_rating) {
        this.book_rating = book_rating;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(int book_cover) {
        this.book_cover = book_cover;
    }
}
