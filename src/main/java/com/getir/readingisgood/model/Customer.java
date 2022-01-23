package com.getir.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Customer")
public class Customer {

    @Transient
    public static final String SEQUENCE_NAME = "SEQ_CUSTOMER";

    @Id
    private Long id;

    private String name;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String email;

}
