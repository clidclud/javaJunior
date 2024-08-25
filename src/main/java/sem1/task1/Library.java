package sem1.task1;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание","Федор Достоевский", 1866));
        books.add(new Book("Евгений Онегин","Александр Пушкин", 1833));
        books.add(new Book("Война и мир","Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита","Михаил Булгаков", 1967));

        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books){
            if ("Лев Толстой".equals(book.getAuthor())){
                authorBooks.add(book);
            }
        }
        System.out.println("Лев Толстой: " + authorBooks);

        List<Book> booksAuthorYear = new ArrayList<>();
        for(Book book : books){
            if (book.getYear() > 1866){
                booksAuthorYear.add(book);
            }
        }
        System.out.println("Книги после 1866: " + booksAuthorYear);

        List<String> uniqueTitles = new ArrayList<>();
        for (Book book : books){
            if (!uniqueTitles.contains(book.getTitle())){
                uniqueTitles.add(book.getTitle());
            }
        }
        System.out.println("Наименование книг: " + uniqueTitles);
    }
}
