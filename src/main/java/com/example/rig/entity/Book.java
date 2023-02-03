package com.example.rig.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_book", allocationSize = 1)
    @GeneratedValue(generator = "seq_book", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "name")
    private String name;

    @Column(length = 100, name = "type")
    private String type;

    @Column(length = 100, name = "author")
    private String author;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Long stock;
}
