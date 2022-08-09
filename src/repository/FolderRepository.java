package repository;

import moduls.classes.Folder;
import moduls.classes.Note;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FolderRepository {

    void saveFolder(Folder folder);
    boolean findAll();
    void save(Folder newFolder);
    void findAllPathOfNote();
    Folder findFolder(String folderName);
    Set<Folder> findFolderInFolder();
    void deleteFolder(String folderName);
    List<Folder> foldersPath(String folderName);
//    Folder findUsersFolder();
    List<String> findFolderPath(String nameFolder);

}
