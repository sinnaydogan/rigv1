package com.example.rig.service;

import com.example.rig.dto.BookDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface BookService {
    BookDto save(BookDto bookDto);

    void delete(Long id);

    List<BookDto> getAll();

    Page<BookDto> getAll(Pageable pageable);

    BookDto stockUpdate(Long bookId, Long stock);
}
