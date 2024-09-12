package com.cydeo.controller;

import com.cydeo.dto.BookDto;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<ResponseWrapper>  getData(){
        return ResponseEntity.ok(new ResponseWrapper("Books are successfully retrieved",bookService.retrieveBook()));
    }

    @GetMapping("{title}")
    public ResponseEntity<ResponseWrapper> findById(@PathVariable ("title")String title) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseWrapper("Book is successfully found", bookService.findByTitle(title)));
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(new ResponseWrapper("Books is successfully added", bookService.addBook(bookDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto){
        return ResponseEntity.ok(new ResponseWrapper("Book is successfully updated",bookService.updateBook(bookDto, id)));
    }


}
