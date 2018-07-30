package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import java.io.*;
import java.util.Set;
import views.html.books.*;
import models.*;
//import javax.inject.Inject;
//import mc.annotations.*;


//A controller class for the Books
public class BooksController extends Controller {

    public static Result index() {
        Set<Book> books;
        books = Book.allBooks();
        return ok(index.render(books));
        //return ok("Hello, new world!");
    }

    public static Result create() {
        return TODO;
    }

    public static Result save() {
        return TODO;
    }

    public static Result show(Integer id) {
        return TODO;
    }

    public static Result edit(Integer id) {
        return TODO;
    }

    public static Result update() {
        return TODO;
    }

    public static Result delete(Integer id) {
        return TODO;
    }
}