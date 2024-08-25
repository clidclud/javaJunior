package sem1.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryV2 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание","Федор Достоевский", 1866));
        books.add(new Book("Евгений Онегин","Александр Пушкин", 1833));
        books.add(new Book("Война и мир","Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита","Михаил Булгаков", 1967));

        List<Book> authorBooks = books.stream().filter(book -> "Лев Толстой".equals(book.getAuthor())).toList();
        System.out.println("Лев Толстой: " + authorBooks);

        List<Book> booksAuthorYear = books.stream().filter(book -> book.getYear() > 1866).toList();
        System.out.println("Книги после 1866: " + booksAuthorYear);

        List<String> uniqueTitles = books.stream().map(Book::getTitle).distinct().toList();
        System.out.println("Наименование книг: " + uniqueTitles);
    }

}
