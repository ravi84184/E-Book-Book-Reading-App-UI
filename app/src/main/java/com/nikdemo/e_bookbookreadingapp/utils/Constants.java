package com.nikdemo.e_bookbookreadingapp.utils;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.BookModel;
import com.nikdemo.e_bookbookreadingapp.model.PlanModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_TYPE = "user_type";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_EMAIL = "user_email";
    public static String sourceRobotoRegular = "fonts/Roboto-Regular.ttf";
    public static String sourceRobotoBold = "fonts/Roboto-Bold.ttf";
    public static String sourceRobotoLight= "fonts/RobotoCondensed-Light.ttf";


    public static String USER_TOKEN= "user_token";

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

    public static ArrayList<PlanModel> getPlan() {
        ArrayList<PlanModel> bookList = new ArrayList<>();
        bookList.add(new PlanModel("20$", "month", 3));
        bookList.add(new PlanModel("30$", "month", 6));
        bookList.add(new PlanModel("50$", "year", 1));
        return bookList;
    }
}
