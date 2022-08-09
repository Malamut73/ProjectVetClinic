package repository.impl;

import connector.Connector;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import moduls.classes.Staff;
import repository.FolderRepository;
import repository.config.ConfigFolder;
import repository.config.ConfigNote;
import repository.config.ConfigLogPass;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FolderRepositoryImpl implements FolderRepository {

    private final static FolderRepository FOLDER_REPOSITORY = new FolderRepositoryImpl();

    private FolderRepositoryImpl(){}

    public static FolderRepository GET_FOLDER_REPOSITORY() {
        return FOLDER_REPOSITORY;
    }

    @Override
    public void saveFolder(Folder folder) {
        String insert = "INSERT INTO " + ConfigFolder.TABLE_FOLDER + " (" +
                ConfigFolder.NAME_FOLDER + ", " +
                ConfigFolder.PARENT_FOLDER + ") " +
                "VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, folder.getName());
            preparedStatement.setString(2, folder.getParentFolderName());
            preparedStatement.executeUpdate();
//            System.out.println(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("New folder was created");
    }

    @Override
    public boolean findAll() {
        return false;
    }

    @Override
    public void save(Folder newFolder) {

    }

    @Override
    public void findAllPathOfNote() {

    }

    @Override
    public Folder findFolder(String name) {


        ResultSet resultSet;
        Folder newFolder = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigFolder.TABLE_FOLDER +
                " WHERE " +
                ConfigFolder.NAME_FOLDER + " =?";
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            while (resultSet.next()){
                String folderName = resultSet.getString(ConfigFolder.NAME_FOLDER);
                String parentFolderName = resultSet.getString(ConfigFolder.PARENT_FOLDER);
                newFolder = new Folder(folderName, parentFolderName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newFolder;
    }

    @Override
    public Set<Folder> findFolderInFolder() {
        Set<Folder> folders = new HashSet<>();
        ResultSet resultSet;
        Folder newFolder = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigFolder.TABLE_FOLDER +
                " WHERE " +
                ConfigFolder.PARENT_FOLDER + " =?";
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, Helper.getCurrentFolder().getName());
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            while (resultSet.next()){
                String folderName = resultSet.getString(ConfigFolder.NAME_FOLDER);
                String parentFolderName = resultSet.getString(ConfigFolder.PARENT_FOLDER);
                newFolder = new Folder(folderName, parentFolderName);
                folders.add(newFolder);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return folders;
    }

    @Override
    public void deleteFolder(String folder) {

        String select = "DELETE FROM " + ConfigFolder.TABLE_FOLDER +
                " WHERE " + ConfigFolder.NAME_FOLDER + " =?";
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, folder);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Folder was deleted");
    }

    @Override
    public List<Folder> foldersPath(String folderName) {

        return null;
    }

    //Возвращает таблицу с админами и созданными ими заметками
    @Override
    public Folder findUsersFolder() {
        ResultSet resultSet;
        Note findNote = null;
//        Folder parentFolder = null;

        String select = "SELECT " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
                ConfigNote.TABLE_NOTE + "." + ConfigNote.NAME_NOTE + ", " +
                ConfigNote.TABLE_NOTE + "." + ConfigNote.TEXT + ", " +
                ConfigNote.TABLE_NOTE + "." + ConfigNote.NAME_PARENT_FOLDER +
                ConfigNote.TABLE_NOTE + "." + ConfigNote.UPDATE_DATE +
                " FROM " + ConfigUsers.USERS_TABLE +
                " INNER JOIN " + ConfigNote.TABLE_NOTE +
                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + "=" +
                ConfigNote.TABLE_NOTE + "." + ConfigNote.USER_ID +
                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE + "=?" +
                " ORDER BY " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME;


        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, "admin");
            resultSet = preparedStatement.executeQuery();
//            System.out.println(preparedStatement);

            while (resultSet.next()){
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middle_name = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String nameNote = resultSet.getString(ConfigNote.NAME_NOTE);
                String text = resultSet.getString(ConfigNote.TEXT);
                String nameFolder = resultSet.getString(ConfigNote.NAME_PARENT_FOLDER);
                Date updateDate = resultSet.getDate(ConfigNote.UPDATE_DATE);

//                Folder folder = findFolder(nameFolder);
                List<String> path = findFolderPath(nameFolder);
                Collections.reverse(path);
                System.out.println("User: " + lastName + " " + firstName + " " + middle_name);
                System.out.println("Name of note: " + nameNote);
                System.out.println("Text of note: " + text);
                System.out.print("Path of note: ");
                for (var str :
                        path) {
                    System.out.print(str + "/");
                }
                System.out.println("\n==========================================");


//                Note noteSearching = NoteRepositoryImpl.GET_NOTE_REPOSITORY().findNote(nameNote);
//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
//                findNote = new Note(idNote, nameFolder, noteName, text, email, creatingDate, updateDate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


        private List<String> findFolderPath(String nameFolder){
        List<String> path = new LinkedList<>();
            if(nameFolder.equals("root")){
                path.add(nameFolder);
                return path;
            }
            folderPath(nameFolder, path);
        return path;
    }

    // это метод для определения пути папки принимает название текущей папки
    private void folderPath(String nameFolder, List<String> path) {

        ResultSet resultSet = null;
            path.add(nameFolder);


        String select = "SELECT " + ConfigFolder.TABLE_FOLDER + "." + ConfigFolder.PARENT_FOLDER +
                " FROM " + ConfigFolder.TABLE_FOLDER +
                " WHERE " + ConfigFolder.TABLE_FOLDER + "." + ConfigFolder.NAME_FOLDER + " =?";
//        System.out.println(select);

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
//            System.out.println(nameFolder);
            preparedStatement.setString(1, nameFolder);
            resultSet = preparedStatement.executeQuery();
//            System.out.println(preparedStatement);

            while (resultSet.next()){
                String parentFolderName = resultSet.getString(ConfigFolder.PARENT_FOLDER);
                if(parentFolderName.equals("root")){
                    path.add(parentFolderName);
                }else {
                    folderPath(parentFolderName, path);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private List<String> getFolderPath(String folderName){
//
//
//        var folder = findFolder(folderName);
//        if(folder.isEmpty()){
//            return null;
//        }
//
//        return findFolderPath(note.get());
////        return null;
//
//    }
//

//
//    private void findFolderPath(List<String> path, Folder folder){
//        path.add(folder.getName());
//
//        if(folder.getParentFolder().getName() != null){
//            findFolderPath(path, folder.getParentFolder());
//        }
//    }


}
