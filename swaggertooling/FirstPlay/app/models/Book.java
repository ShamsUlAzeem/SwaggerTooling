package models;


import java.util.HashSet;
import java.util.Set;
//import com.avaje.ebean.Model;

//A class that defines a Book. And instantiates a list of books for testing purpose.
public class Book {

    public Integer id;
    public String title;
    public Integer price;
    public String author;
    private static Set<Book> books;

    public Book() { }

    public Book (Integer id, String title, String author, Integer price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    static {
        books = new HashSet<>();
        books.add(new Book(2, "C++", "ABC", 20));
        books.add(new Book(1, "Java", "DEF", 30));
    }

    public static Set<Book> allBooks() {
        return books;
    }

    public static Book findById(Integer id) {
        for(Book book : books) {
            if(book.id.equals(id)) {
                return book;
            }
        }
        return null;
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static boolean removeBook(Book book) {
        return books.remove(book);
    }


}