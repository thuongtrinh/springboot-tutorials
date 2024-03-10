package com.txt.wssoap.producer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "articles")
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private long articleId;

    @Column
    private String title;

    @Column
    private String category;

}
