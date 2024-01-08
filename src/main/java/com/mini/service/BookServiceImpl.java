package com.mini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.entity.Book;
import com.mini.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {

		List<Book> books = bookRepository.findAll();

		return books;
	}

	@Override
	public Book getBook(Long bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			return book.get();
		}
		return null;
	}

	@Override
	public Book createBook(Book book) {
		bookRepository.save(book);
		return book;
	}

	@Override
	public Book updateBook(Long id, Book book) {
		if(bookRepository.existsById(id)) {
			bookRepository.save(book);
			book.setBookId(id);
			return bookRepository.save(book);
		}
		return null;
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);

	}

}
