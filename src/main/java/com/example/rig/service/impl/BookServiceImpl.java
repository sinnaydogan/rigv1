package com.example.rig.service.impl;

import com.example.rig.dto.BookDto;
import com.example.rig.entity.Book;
import com.example.rig.repository.BookRepository;
import com.example.rig.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setType(bookDto.getType());
        book.setPrice(bookDto.getPrice());
        book.setStock(bookDto.getStock());

        bookRepository.save(book);
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<BookDto> getAll() {
        return null;
    }

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public BookDto stockUpdate(Long bookId, Long stock) {
        Book book = bookRepository.getById(bookId);
        book.setStock(stock);
        bookRepository.save(book);

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        bookDto.setPrice(book.getPrice());
        bookDto.setStock(book.getStock());
        return bookDto;
    }
}
