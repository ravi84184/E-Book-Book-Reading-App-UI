package com.nikdemo.e_bookbookreadingapp.utils;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.BookModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static String sourceRobotoRegular = "fonts/Roboto-Regular.ttf";
    public static String sourceRobotoBold = "fonts/Roboto-Bold.ttf";
    public static String sourceRobotoLight= "fonts/RobotoCondensed-Light.ttf";

    public static ArrayList<BookModel> getBookData() {
        ArrayList<BookModel> bookList = new ArrayList<>();
        bookList.add(new BookModel("Autobiography", "", 4.5, false, R.drawable.a));
        bookList.add(new BookModel("Big Read", "", 3.5, true, R.drawable.b));
        bookList.add(new BookModel("Bibliography of jazz", "", 4, true, R.drawable.c));
        bookList.add(new BookModel("Blackberry Winter: My Earlier Years", "", 2.5, false, R.drawable.d));
        bookList.add(new BookModel("An Interview", "", 5, false, R.drawable.a));
        bookList.add(new BookModel("Filling the Gap", "", 3, true, R.drawable.b));
        bookList.add(new BookModel("Where Have I Been?", "", 4, true, R.drawable.c));
        bookList.add(new BookModel("Fatherhood", "", 2, false, R.drawable.d));
        return bookList;
    }

    public static ArrayList<String> getCat() {
        ArrayList<String> catList= new ArrayList<>();
        catList.add("Arts & Music");
        catList.add("Painting");
        catList.add("Biographies");
        catList.add("Computers & Tech");
        catList.add("Action & Adventure");
        catList.add("Kids");
        catList.add("Economics");
        catList.add("Business");
        return catList;
    }
}
