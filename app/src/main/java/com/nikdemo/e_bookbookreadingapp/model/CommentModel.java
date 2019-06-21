package com.nikdemo.e_bookbookreadingapp.model;

public class CommentModel {

    private String user_name,comment;
    private int user_image;

    public CommentModel(String user_name, String comment, int user_image) {
        this.user_name = user_name;
        this.comment = comment;
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUser_image() {
        return user_image;
    }

    public void setUser_image(int user_image) {
        this.user_image = user_image;
    }
}
