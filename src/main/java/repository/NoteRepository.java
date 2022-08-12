package repository;

import moduls.classes.Folder;
import moduls.classes.Note;
import moduls.classes.User;

import java.util.List;
import java.util.Set;

public interface NoteRepository {

    void saveNote(Note note);
    Note findNote(String nameNote);
    Set<Note> findNotes(Folder folder);
    List<Note> findNotes(User staff);


}
