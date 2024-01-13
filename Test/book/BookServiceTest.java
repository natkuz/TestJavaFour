package seminars.fourth.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    /*
    У вас есть класс BookService, который использует интерфейс BookRepository для получения информации о книгах
    из базы данных. Ваша задача написать unit-тесты для BookService,
    используя Mockito для создания мок-объекта BookRepository.
     */

    BookRepository bookRepositoryMock;
    BookService bookService;

    @BeforeEach
    void setup() {
        bookRepositoryMock = mock(BookRepository.class);
        bookService = new BookService(bookRepositoryMock);
    }

    @Test
    void bookFindTest() {
        when(bookRepositoryMock.findById("1")).thenReturn(new Book("1", "Book1", "Author1"));

        Book result = bookService.findBookById("1");

        assertEquals("Book1", result.getTitle());
        assertEquals("Author1", result.getAuthor());
    }

    @Test
    void bookFindAllTest() {
        when(bookRepositoryMock.findAll()).thenReturn(List.of(new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")));

        List<Book> result = bookService.findAllBooks();

        assertEquals("Book1", result.get(0).getTitle());
        assertEquals("Author1", result.get(0).getAuthor());
        assertEquals("Book2", result.get(1).getTitle());
        assertEquals("Author2", result.get(1).getAuthor());
    }
}