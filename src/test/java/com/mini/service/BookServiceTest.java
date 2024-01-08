package com.mini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mini.entity.Book;
import com.mini.repo.BookRepository;

public class BookServiceTest {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "Book 1", "Author 1", 2022));
		books.add(new Book(2L, "Book 2", "Author 2", 2023));

		when(bookRepository.findAll()).thenReturn(books);

		List<Book> result = bookService.getAllBooks();

		assertEquals(2, result.size());
	}

	@Test
	void testGetBookById() {
		Long bookId = 1L;
		Book book = new Book(bookId, "Book 1", "Author 1", 2022);

		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

		Book result = bookService.getBook(bookId);

		assertEquals(bookId, result.getBookId());
	}

	@Test
	void testCreateBook() {
		Book newBook = new Book(null, "New Book", "New Author", 2024);

		when(bookRepository.save(newBook)).thenReturn(newBook);

		Book result = bookService.createBook(newBook);

		assertEquals(newBook.getTitle(), result.getTitle());
	}

	@Test
	void testUpdateBook() {
		Long bookId = 1L;
		Book existingBook = new Book(bookId, "Book 1", "Author 1", 2022);
		Book updatedBook = new Book(bookId, "Updated Book", "Updated Author", 2023);

		when(bookRepository.existsById(bookId)).thenReturn(true);
		when(bookRepository.save(updatedBook)).thenReturn(updatedBook);

		Book result = bookService.updateBook(bookId, updatedBook);

		assertEquals(updatedBook.getTitle(), result.getTitle());
	}

	@Test
	void testUpdateNonExistingBook() {
		Long nonExistingBookId = 100L;
		Book updatedBook = new Book(nonExistingBookId, "Updated Book", "Updated Author", 2023);

		when(bookRepository.existsById(nonExistingBookId)).thenReturn(false);

		Book result = bookService.updateBook(nonExistingBookId, updatedBook);

		assertEquals(null, result);
	}

	@Test
	void testDeleteBook() {
		Long bookId = 1L;

		bookService.deleteBook(bookId);

		verify(bookRepository, times(1)).deleteById(bookId);
	}
}