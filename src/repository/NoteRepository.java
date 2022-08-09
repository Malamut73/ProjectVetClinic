package repository;

import moduls.classes.Note;

import java.util.Optional;
import java.util.Set;

public interface NoteRepository {

    void saveNote(Note note);
    Note findNote(String nameNote);
    Set<Note> findNoteInFolder();


}
