package ru.gb.homework_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework_6.model.Note;

import java.util.Optional;
@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
    public Optional<Note> findById(Long id);
}
