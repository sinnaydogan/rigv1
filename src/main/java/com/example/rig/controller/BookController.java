package com.example.rig.controller;


import com.example.rig.dto.BookDto;
import com.example.rig.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookDto>> get() {
        return ResponseEntity.ok(bookService.getAll());
    }
    @PutMapping("/stock-update/{bookId}")
    public ResponseEntity<BookDto> stockUpdate(@PathVariable Long bookId, @RequestBody Long stock) {
        return ResponseEntity.ok(bookService.stockUpdate(bookId, stock));
    }
}
