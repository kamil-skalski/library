package com.ks.library.endpoints;

import com.ks.library.payloads.request.CreateBookRequestDTO;
import com.ks.library.payloads.response.BookResponseDTO;
import com.ks.library.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookEndpoint {

    private final BookService bookService;

    public BookEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody CreateBookRequestDTO createBookRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.create(createBookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }
}