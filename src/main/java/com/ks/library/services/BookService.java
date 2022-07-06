package com.ks.library.services;

import com.ks.library.endpoints.BookEndpoint;
import com.ks.library.entities.BookEntity;
import com.ks.library.payloads.request.CreateBookRequestDTO;
import com.ks.library.payloads.response.BookResponseDTO;
import com.ks.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO create(CreateBookRequestDTO createBookRequestDTO) {
        BookEntity bookEntity = bookRepository.save(
                new BookEntity(createBookRequestDTO.getTitle(), createBookRequestDTO.getAuthor(), createBookRequestDTO.getNumberOfPages()));
        return new BookResponseDTO(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getNumberOfPages());
    }
}
