package com.cydeo.repository;

import com.cydeo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
BookRepository extends JpaRepository<Book, Long>{

   Book findByTitle(String name);

}

