package repository.impl;

import connector.Connector;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import moduls.classes.Staff;
import repository.NoteRepository;
import repository.config.ConfigNote;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class NoteRepositoryImpl implements NoteRepository {

    private final static NoteRepository NOTE_REPOSITORY = new NoteRepositoryImpl();
    private NoteRepositoryImpl(){}

    public static NoteRepository GET_NOTE_REPOSITORY() {
        return NOTE_REPOSITORY;
    }

    @Override
    public void saveNote(Note note) {


        String insert = "INSERT INTO " + ConfigNote.TABLE_NOTE + " (" +
                ConfigNote.NAME_NOTE + ", " +
                ConfigNote.TEXT + ", " +
                ConfigNote.NAME_PARENT_FOLDER + ", " +
                ConfigNote.USER_ID + ") " +
                "VALUES(?, ?, ?, ?)";

        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getText());
            preparedStatement.setString(3, note.getParentFolderName());
            preparedStatement.setInt(4, note.getUserId());
            preparedStatement.executeUpdate();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Note was created");

    }

    @Override
    public Note findNote(String nameNote) {

        ResultSet resultSet;
        Note findNote = null;
        Staff staff = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigNote.TABLE_NOTE +
                " WHERE " +
                ConfigNote.NAME_NOTE + " =?";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, nameNote);
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            while (resultSet.next()){
                int idNote = resultSet.getInt(ConfigNote.ID_NOTE);
                String noteName = resultSet.getString(ConfigNote.NAME_NOTE);
                String text = resultSet.getString(ConfigNote.TEXT);
                Date creatingDate = resultSet.getDate(ConfigNote.CREATION_DATE);
                Date updateDate = resultSet.getDate(ConfigNote.UPDATE_DATE);
                String nameFolder = resultSet.getString(ConfigNote.NAME_PARENT_FOLDER);
                int userId = resultSet.getInt(ConfigNote.USER_ID);

                staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().findStaff(userId);
                if(staff == null){
                    System.out.println("Staff not found");
                }

//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate,staff);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return findNote;
    }

    @Override
    public Set<Note> findNotes(Folder folder) {

        Set<Note> notes = new HashSet<>();

        ResultSet resultSet;
        Note findNote = null;
        Staff staff = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigNote.TABLE_NOTE +
                " WHERE " +
                ConfigNote.NAME_PARENT_FOLDER + " =?";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, folder.getName());
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            while (resultSet.next()){
                int idNote = resultSet.getInt(ConfigNote.ID_NOTE);
                String noteName = resultSet.getString(ConfigNote.NAME_NOTE);
                String text = resultSet.getString(ConfigNote.TEXT);
                Date creatingDate = resultSet.getDate(ConfigNote.CREATION_DATE);
                Date updateDate = resultSet.getDate(ConfigNote.UPDATE_DATE);
                String nameFolder = resultSet.getString(ConfigNote.NAME_PARENT_FOLDER);
                String email = resultSet.getString(ConfigNote.EMAIL);
                int userId = resultSet.getInt(ConfigNote.USER_ID);

                staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().findStaff(userId);
                if(staff == null){
                    System.out.println("Staff not found");
                }

//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate, staff);
                notes.add(findNote);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notes;
    }

    @Override
    public List<Note> findNotes(Staff staff) {
        List<Note> notes = new LinkedList<>();
        ResultSet resultSet;
        Note findNote = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigNote.TABLE_NOTE +
                " WHERE " +
                ConfigNote.USER_ID + " =?" +
                " ORDER BY " + ConfigNote.UPDATE_DATE +
                " DESC";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setInt(1, staff.getUserId());
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            while (resultSet.next()){
                int idNote = resultSet.getInt(ConfigNote.ID_NOTE);
                String noteName = resultSet.getString(ConfigNote.NAME_NOTE);
                String text = resultSet.getString(ConfigNote.TEXT);
                Date creatingDate = resultSet.getDate(ConfigNote.CREATION_DATE);
                Date updateDate = resultSet.getDate(ConfigNote.UPDATE_DATE);
                String nameFolder = resultSet.getString(ConfigNote.NAME_PARENT_FOLDER);
                int userId = resultSet.getInt(ConfigNote.USER_ID);


//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate,staff);
                notes.add(findNote);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        Collections.reverse(notes);
        return notes;
    }


}
