package com.stackroute.recommendations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.recommendations.service.BookService;



@RestController
@RequestMapping("/api/v3/books")
public class DataController {
  private final BookService bookService;

  @Autowired
  public DataController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping(value={"", "/recomended"})
  public ResponseEntity<?> recomended(){
      var output = bookService.consumeApi("https://openlibrary.org/trending/daily.json?limit=10");
    return ResponseEntity.status(HttpStatus.OK).body(output);
  }

  @GetMapping(value={"", "/search"})
  public ResponseEntity<?> search(@RequestParam(value = "q") String q){
    var output = bookService.consumeApi("https://openlibrary.org/search.json?q="+q+"&limit=10");
    return ResponseEntity.status(HttpStatus.OK).body(output);
  }
  @GetMapping(value={"", "/search-author"})
  public ResponseEntity<?> authors(@RequestParam(value = "q") String q){
    var output = bookService.consumeApi("https://openlibrary.org/search.json?author="+q);
    return ResponseEntity.status(HttpStatus.OK).body(output);
  }
 
}

