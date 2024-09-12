package com.cydeo.service;

import com.cydeo.dto.BookDto;


import java.util.List;

public interface BookService {

   BookDto addBook(BookDto bookDto);
   List<BookDto> retrieveBook();
   BookDto updateBook(BookDto bookDto,Long id);
   void deleteBook(Long id);

    BookDto findByTitle(String title);
}
