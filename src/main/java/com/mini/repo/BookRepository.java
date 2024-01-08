package com.mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
