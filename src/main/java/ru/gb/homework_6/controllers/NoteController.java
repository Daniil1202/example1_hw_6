package ru.gb.homework_6.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework_6.model.Note;
import ru.gb.homework_6.repository.NotesRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NotesRepository repository;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){   // создание заметки
       return new ResponseEntity<>(repository.save(note), HttpStatus.CREATED);
    }
    @GetMapping
    public  ResponseEntity<List<Note>> getAllNotes(){ // получение всех заметок
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){  //поиск заметки по id
        Optional<Note> noteOptional = repository.findById(id);
        if (noteOptional.isPresent()){   // проверка на наличия значения
            return new ResponseEntity<>(noteOptional.get(),HttpStatus.FOUND);

        }else {
            return ResponseEntity.notFound().build(); //  в случае отсутсвия
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNotesById(@PathVariable Long id,@RequestBody Note note ) {   //редактирование заметки по id
        Optional<Note> noteOptional = repository.findById(id); // поиск нужной заметки
        try {
            Note noteFound= noteOptional.get(); // получение заметки
            noteFound.setTitle(note.getTitle());
            noteFound.setText(note.getText());
            return new ResponseEntity<>(repository.save(noteFound),HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Note());
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){ //удаление заметки по id
        Optional<Note> noteOptional =  repository.findById(id);
        if (noteOptional.isPresent()){
            repository.delete(noteOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
