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

//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate,userId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return findNote;
    }

    @Override
    public Set<Note> findNoteInFolder() {

        Set<Note> notes = new HashSet<>();

        ResultSet resultSet;
        Note findNote = null;
//        Folder parentFolder = null;

        String select = "SELECT * FROM " +
                ConfigNote.TABLE_NOTE +
                " WHERE " +
                ConfigNote.NAME_PARENT_FOLDER + " =?";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, Helper.getCurrentFolder().getName());
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

//                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate, userId);
                notes.add(findNote);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notes;
    }

    @Override
    public List<Note> findNoteByStaff (Staff staff) {
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
                findNote = new Note(idNote, nameFolder, noteName, text, creatingDate, updateDate,userId);
                notes.add(findNote);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        Collections.reverse(notes);
        return notes;
    }

    @Override
    public boolean findAllNotesAllUsers(){
        ResultSet resultSet;
        Staff staff = null;


        String select = "SELECT " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME +
                " FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE + "=?" +
                " ORDER BY " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME +
                " ASC";

        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, "admin");
            resultSet = preparedStatement.executeQuery();
//            System.out.println(preparedStatement);

            while (resultSet.next()){
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middle_name = resultSet.getString(ConfigUsers.MIDDLE_NAME);

                staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().getStaff(new Staff(lastName, firstName, middle_name));
                List<Note> notes = NoteRepositoryImpl.GET_NOTE_REPOSITORY().findNoteByStaff(staff);


                for (Note note :
                        notes) {
                    System.out.println("User: " + lastName + " " + firstName + " " + middle_name);
                    System.out.println("Name of note: " + note.getName());
                    System.out.println("Date of update: " + note.getUpdateDate());
                    System.out.println("Text of note: " + note.getText());
                    System.out.print("Path of note: ");
                    for (String str :
                            FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolderPath(note.getParentFolderName())) {
//                        Collections.reverse(path);

                        System.out.print(str + "/");

                    }
                    System.out.println("\n==========================================");

                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return staff == null ? true : false ;
    }

    @Override
    public boolean findAllUserNotesInFolder(Folder folder) {



        Staff staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().getStaff(Helper.getStaff());
        List<Note> notes = findNoteByStaff(staff);
        for (Note note:
             notes  ) {
            if(note.getParentFolderName().equals(folder.getName())){
                System.out.println("User: " + staff.getLastName() + " " + staff.getFirstName() + " " + staff.getLastName());
                System.out.println("Name of note: " + note.getName());
                System.out.println("Date of update: " + note.getUpdateDate());
                System.out.println("Text of note: " + note.getText());
                for (String str :
                        FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolderPath(note.getParentFolderName())) {
//                        Collections.reverse(path);

                    System.out.print(str + "/");

                }
                System.out.println("\n==========================================");
            }
        }




        return notes.isEmpty() ? true : false;
    }


}
