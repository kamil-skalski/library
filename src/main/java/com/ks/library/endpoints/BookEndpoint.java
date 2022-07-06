package com.ks.library.endpoints;

import com.ks.library.payloads.request.CreateBookRequestDTO;
import com.ks.library.payloads.response.BookResponseDTO;
import com.ks.library.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(tags = "Books")
public class BookEndpoint {

    private final BookService bookService;

    public BookEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ApiOperation("Create book")
    public ResponseEntity<BookResponseDTO> create(@RequestBody CreateBookRequestDTO createBookRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.create(createBookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find book")
    public ResponseEntity<BookResponseDTO> find(@PathVariable Long id) {
        BookResponseDTO bookResponseDTO = bookService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @GetMapping()
    @ApiOperation("Find all books")
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        List<BookResponseDTO> bookResponseDTO = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }
}
