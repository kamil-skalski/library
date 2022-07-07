package com.ks.library.services;

import com.ks.library.entities.BookEntity;
import com.ks.library.exeptions.BookNotFoundException;
import com.ks.library.payloads.response.BookResponseDTO;
import com.ks.library.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class BookServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public BookService employeeService() {
            return new BookService();
        }
    }

    @Autowired
    BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        BookEntity b1 = new BookEntity("b1","a1", 1);
        b1.setId(11L);

        BookEntity b2 = new BookEntity("b2","a2", 2);
        BookEntity b3 = new BookEntity("b3","a3", 3);

        List<BookEntity> allBooks = Arrays.asList(b1, b2, b3);

        Mockito.when(bookRepository.findById(b1.getId())).thenReturn(Optional.of(b1));
        Mockito.when(bookRepository.findAll()).thenReturn(allBooks);
        Mockito.when(bookRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(bookRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(bookRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(bookRepository);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        BookResponseDTO b = bookService.find(11L);
        BookEntity bookFromDb = new BookEntity(b.getTitle(), b.getAuthor(), b.getNumberOfPages());
        Assertions.assertThat(bookFromDb.getTitle()).isEqualTo("b1");
        Assertions.assertThat(bookFromDb.getAuthor()).isEqualTo("a1");
        Assertions.assertThat(bookFromDb.getNumberOfPages()).isEqualTo(1);

        verifyFindByIdIsCalledOnce();
    }

    @Test(expected = BookNotFoundException.class)
    public void whenInValidId_thenEmployeeShouldNotBeFound() throws BookNotFoundException {
        BookResponseDTO b = bookService.find(-99L);
        verifyFindByIdIsCalledOnce();
        Assertions.assertThat(b).isNull();
    }

    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        BookEntity b1 = new BookEntity("b1","a1", 1);
        BookEntity b2 = new BookEntity("b2","a2", 2);
        BookEntity b3 = new BookEntity("b3","a3", 3);

        List<BookEntity> allBooks = bookService.findAll().
                stream()
                .map(item -> new BookEntity(item.getTitle(), item.getAuthor(), item.getNumberOfPages()))
                .collect(Collectors.toList());
        verifyFindAllEmployeesIsCalledOnce();
        Assertions.assertThat(allBooks)
                .hasSize(3)
                .extracting(BookEntity::getTitle)
                .contains(b1.getTitle(), b2.getTitle(), b3.getTitle());
    }
}