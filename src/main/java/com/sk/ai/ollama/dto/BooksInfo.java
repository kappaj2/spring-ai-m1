package com.sk.ai.ollama.dto;

public record BooksInfo(
        String category,
        String book,
        String year,
        String review,
        String author,
        String summary
) {};