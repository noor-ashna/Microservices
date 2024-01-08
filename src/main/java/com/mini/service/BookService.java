package com.mini.service;

import java.util.List;

import com.mini.entity.Book;

public interface BookService {

	public List<Book> getAllBooks();

	public Book getBook(Long bookId);

	public Book createBook(Book book);

	public Book updateBook(Long id, Book book);

	public void deleteBook(Long id);

}
