package repository;

import moduls.classes.Folder;
import moduls.classes.Note;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FolderRepository {

    void saveFolder(Folder folder);
    Folder findFolder(String folderName);
    Set<Folder> findFolder(Folder folder);
    List<String> findFolderPath(String nameFolder);

}
