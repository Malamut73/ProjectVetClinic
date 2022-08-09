package repository;

import moduls.classes.Folder;
import moduls.classes.Note;
import moduls.classes.Staff;

import java.util.List;
import java.util.Set;

public interface NoteRepository {

    void saveNote(Note note);
    Note findNote(String nameNote);
    Set<Note> findNoteInFolder();
    List<Note> findNoteByStaff (Staff staff);
    boolean findAllNotesAllUsers();
    boolean findAllUserNotesInFolder(Folder folder);


}
