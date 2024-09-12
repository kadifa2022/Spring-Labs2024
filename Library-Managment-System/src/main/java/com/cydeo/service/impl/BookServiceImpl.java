package com.cydeo.service.impl;

import com.cydeo.dto.BookDto;
import com.cydeo.entity.Book;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.BookRepository;
import com.cydeo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MapperUtil mapperUtil;

    public BookServiceImpl(BookRepository bookRepository, MapperUtil mapperUtil) {
        this.bookRepository = bookRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = mapperUtil.convert(bookDto, new Book());
        bookRepository.save(book);
        return mapperUtil.convert(book, new BookDto());
    }

    @Override
    public List<BookDto> retrieveBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> mapperUtil.convert(book, new BookDto())).collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long id) {
        Book bookFound = bookRepository.findById(id).get();
        bookFound.setAuthor(bookDto.getAuthor());
        bookFound.setTitle(bookDto.getTitle());
        bookFound.setIsbn(bookDto.getIsbn());
        bookFound.setAvailable(true);
        bookRepository.save(bookFound);

        return mapperUtil.convert(bookFound, new BookDto());
    }

    @Override
    public void deleteBook(Long id) {
      bookRepository.deleteById(id);
    }

    @Override
    public BookDto findByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        return mapperUtil.convert(book, new BookDto());
    }


}
