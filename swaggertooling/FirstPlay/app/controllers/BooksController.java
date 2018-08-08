package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.Set;
import views.html.books.*;
import javax.inject.Inject;


public class BooksController extends Controller {
    static int book_inprocess;
    @Inject
    FormFactory formFactory;

    public Result index() {
        Set<Book> books;
        books = Book.allBooks();
        return ok(index.render(books))  ;
        //return ok("Hello, new world!");
    }

    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(create.render(bookForm));
    }

    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        book.addBook(book);
        return redirect(routes.BooksController.index());
    }

    public Result show(Integer id) {
        Book book = Book.findById(id);
        if(book == null) {
            return notFound("Book not found!");
        } else {
            book_inprocess = book.id;
            Form<Book> bookForm = formFactory.form(Book.class);
            return ok(show.render(book));
        }
    }

    public Result edit(Integer id) {
        Book book_to_edit = Book.findById(id);
        if(book_to_edit == null) {
            return notFound("Book not found!");
        } else {
            book_inprocess = id;
            Form<Book> bookForm = formFactory.form(Book.class).fill(book_to_edit);
            return ok(edit.render(bookForm));
        }
    }

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book new_book = bookForm.get();
        Book old_book = Book.findById(book_inprocess);
        if(old_book == null) {
            return internalServerError("Multiple edit request confused the server!");
        } else {
            old_book.id = new_book.id;
            old_book.author = new_book.author;
            old_book.title = new_book.title;
            old_book.price = new_book.price;
            return redirect(routes.BooksController.index());
        }
    }

    public Result delete(Integer id) {
        Book.removeBook(Book.findById(id));
        return redirect(routes.BooksController.index());
    }

    public Result json() {
        Set<Book> books = Book.allBooks();
        ObjectMapper mapper = new ObjectMapper();
        String s;
        return ok("testing");
        /*
        try {
            s = mapper.writeValueAsString(Book.findById(1));
            return ok(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return internalServerError();
        */
    }
}