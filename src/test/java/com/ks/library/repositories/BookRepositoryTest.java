package com.ks.library.repositories;

import com.ks.library.entities.BookEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindById_thenReturnBook() {
        BookEntity bookEntity = new BookEntity("book", "author name", 222);
        entityManager.persistAndFlush(bookEntity);

        BookEntity fromDb = bookRepository.findById(bookEntity.getId()).orElse(null);
        Assertions.assertThat(fromDb.getTitle()).isEqualTo(bookEntity.getTitle());
        Assertions.assertThat(fromDb.getAuthor()).isEqualTo(bookEntity.getAuthor());
        Assertions.assertThat(fromDb.getNumberOfPages()).isEqualTo(bookEntity.getNumberOfPages());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        BookEntity fromDb = bookRepository.findById(-11l).orElse(null);
        Assertions.assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        BookEntity b1 = new BookEntity("book1", "a1", 111);
        BookEntity b2 = new BookEntity("book2", "a2", 222);
        BookEntity b3 = new BookEntity("book3", "a3", 333);

        entityManager.persist(b1);
        entityManager.persist(b2);
        entityManager.persist(b3);
        entityManager.flush();

        List<BookEntity> allBooks = bookRepository.findAll();

        Assertions.assertThat(allBooks)
                .hasSize(3)
                .extracting(BookEntity::getTitle)
                .containsOnly(b1.getTitle(), b2.getTitle(), b3.getTitle());
    }
}