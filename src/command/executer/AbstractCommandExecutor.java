package command.executer;

import repository.*;
import repository.impl.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public abstract class AbstractCommandExecutor implements CommandExecutor {


    protected final ClientRepository clientRepository = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL();
    protected final StaffRepository staffRepository = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL();
    protected final AppointmentRepository appointmentRepository = AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL();
    protected final FolderRepository folderRepository = FolderRepositoryImpl.GET_FOLDER_REPOSITORY();
    protected final NoteRepository noteRepository = NoteRepositoryImpl.GET_NOTE_REPOSITORY();

    protected AbstractCommandExecutor(){
    }

/*
        // ищем заметку по именни в репозитории
    protected Optional<Note> findNote(String noteName) {
        for (Note note : noteRepository.findAll()) {
            if (note.getName().equals(noteName)) {
                return Optional.of(note);
            }
        }

        return Optional.empty();
    }


// ищем папку по имени папки в репозитории
    protected Optional<Folder> findFolder(String folderName) {
        for (var folder : folderRepository.findAll()) {
            if (folder.getName().equals(folderName)) {
                return Optional.of(folder);
            }
        }

        return Optional.empty();
    }

    Получаем путь к папке через имя заметки
    если по имени ни чего не найденно цыкл заканчивается
    protected List<String> findFolderPath(String name) {
        var note = findNote(name);

        if (note.isEmpty()) {
            return null;
        }

        return findFolderPath(note.get());
    }
    ищем путь к к папке через объект заметка
    private List<String> findFolderPath(Note note) {
        List<String> path = new LinkedList<>();

        findFolderPath(note.getParentFolder(), path);

        return path;
    }

    private void findFolderPath(Folder folder, List<String> path) {
        path.add(folder.getName());

        if (folder.getParentFolder() != null) {
            findFolderPath(folder.getParentFolder(), path);
        }
    }
    */

}
