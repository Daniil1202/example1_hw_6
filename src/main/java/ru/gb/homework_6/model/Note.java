package ru.gb.homework_6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity   // сущность
@Table(name="notes")

public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация id
    private Long id;

    @Column(nullable = false)  //название заголовка (не должен быть пустым)
    private String title;

    @Column(nullable = false) // содержимое (не должно быть пустым)
    private String text;

    @Column(name = "CREATE_DATE",nullable = false)  // дата
    private LocalDateTime localDateTime;

    @PrePersist
    public void setLocalDateTime(){
        this.localDateTime = localDateTime.now();
    }

}
