package ru.gb_spring.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb_spring.books.models.Book;
import ru.gb_spring.books.repositories.BookRepository;
import ru.gb_spring.books.services.BookService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Класс тестирования сервиса книг
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	public BookService bookService;
	@Mock
	private BookRepository bookRepository;

	private Book book1;

	/**
	 * Задаём начальные настройки параметров
	 */
	@BeforeEach
	public void setup() {
		book1 = new Book();
		book1.setId(1L);
		book1.setTitle("Title1");
		book1.setAuthor("Author1");
		book1.setDescription("Description1");
	}

	/**
	 * Тест запроса страницы с книгами
	 */
	@Test
	public void getAllBookTest() {

		Mockito.when(bookRepository.findAll()).thenReturn(Collections.singletonList(book1));

		List<Book> notes = bookService.getAllBooks();

		// Проверка вызова метода findAll и корректности полученных данных
		verify(bookRepository).findAll();
		assertThat(notes).isNotEmpty();
		assertThat(notes).hasSize(1);
		assertThat(notes.get(0).getId()).isEqualTo(book1.getId());

	}

}
