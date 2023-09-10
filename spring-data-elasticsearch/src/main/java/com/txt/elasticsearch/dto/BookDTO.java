package com.txt.elasticsearch.dto;

import com.txt.elasticsearch.aspect.PublicationYear;
import com.txt.elasticsearch.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotBlank
    private String title;

    @Positive
    @PublicationYear
    private Integer publicationYear;

    @NotBlank
    private String authorName;

    @NotBlank
    private String isbn;

    public static Book transform(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title);
        book.setPublicationYear(bookDto.publicationYear);
        book.setAuthorName(bookDto.authorName);
        book.setIsbn(bookDto.isbn);
        return book;
    }
}
