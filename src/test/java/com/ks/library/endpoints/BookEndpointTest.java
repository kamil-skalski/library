package com.ks.library.endpoints;

import com.ks.library.LibraryApplication;
import com.ks.library.entities.BookEntity;
import com.ks.library.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LibraryApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class BookEndpointTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void resetDb() {
        bookRepository.deleteAll();
    }

    private void createTestBook(String title, String author, int numberOfPages) {
        BookEntity bookEntity = new BookEntity(title, author, numberOfPages);
        bookRepository.saveAndFlush(bookEntity);
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestBook("book1", "name1", 123);
        createTestBook("book2", "name2", 300);

        mvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].title", is("book1")))
                .andExpect(jsonPath("$[0].author", is("name1")))
                .andExpect(jsonPath("$[0].numberOfPages", is(123)))
                .andExpect(jsonPath("$[1].title", is("book2")))
                .andExpect(jsonPath("$[1].author", is("name2")))
                .andExpect(jsonPath("$[1].numberOfPages", is(300)));
    }
}