package com.getir.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Book")
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "SEQ_BOOK";

    @Id
    private Long id;

    // TODO: 22/01/2022 validations
    @Size(max = 200)//todo handle
    private String name;

    private int count;
}
