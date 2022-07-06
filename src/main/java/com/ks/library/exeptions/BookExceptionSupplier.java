package com.ks.library.exeptions;

import java.util.function.Supplier;

public class BookExceptionSupplier {
    public static Supplier<BookNotFoundException> bookNotFound(Long id) {
        return () -> new BookNotFoundException(id);
    }
}