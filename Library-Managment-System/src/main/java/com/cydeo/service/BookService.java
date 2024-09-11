package com.cydeo.service;

import com.cydeo.dto.BookDto;
import com.cydeo.entity.Book;

import java.util.List;

public interface BookService {

   BookDto addBook(BookDto bookDto);
   List<BookDto> retrieveBook();
   BookDto updateBook(BookDto bookDto,Long id);
   void deleteBook(Long id);

}
