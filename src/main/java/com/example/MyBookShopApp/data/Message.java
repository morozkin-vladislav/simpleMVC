package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(name = "user_id")
    private Integer userId;

    private String email;

    private String name;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String text;

}
