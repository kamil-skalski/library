package com.ks.library.services;

import com.ks.library.endpoints.BookEndpoint;
import com.ks.library.entities.BookEntity;
import com.ks.library.exeptions.BookExceptionSupplier;
import com.ks.library.payloads.request.CreateBookRequestDTO;
import com.ks.library.payloads.request.UpdateBookRequestDTO;
import com.ks.library.payloads.response.BookResponseDTO;
import com.ks.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public BookResponseDTO find(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(BookExceptionSupplier.bookNotFound(id));
        return new BookResponseDTO(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getNumberOfPages());
    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(item -> new BookResponseDTO(item.getId(), item.getTitle(), item.getAuthor(), item.getNumberOfPages()))
                .collect(Collectors.toList());
    }

    public BookResponseDTO delete(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(BookExceptionSupplier.bookNotFound(id));
        bookRepository.deleteById(bookEntity.getId());
        return new BookResponseDTO(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getNumberOfPages());
    }

    public BookResponseDTO update(UpdateBookRequestDTO updateBookRequestDTO) {
        BookEntity bookEntity = bookRepository.findById(updateBookRequestDTO.getId()).orElseThrow(BookExceptionSupplier.bookNotFound(updateBookRequestDTO.getId()));
        bookRepository.save(
                new BookEntity(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getNumberOfPages()));
        return new BookResponseDTO(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getNumberOfPages());
    }
}
