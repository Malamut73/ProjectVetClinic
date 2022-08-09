package vetClinic;


import command.CommandReader;
import connector.Connector;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import repository.config.ConfigFolder;
import repository.config.ConfigLogPass;
import repository.config.ConfigNote;
import repository.config.ConfigUsers;
import repository.impl.FolderRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Main {

    public static void main(String[] args) {

        /*/
        Логин и пароль для входа
        login: admin
        pass: admin

        commands:

        create client lekhmanov Nikolay Igorevich admin
        create client Rodionov Ivan Vladimirovich admin
        create staff Skitin Artem Mihailovich admin
        create staff Filipov Aleksey Mihailovich admin
        create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 03.07.2022 22:00
        view clients
        view staff
        view client appointments Rodionov Ivan Vladimirovich
        view client appointments lekhmanov Nikolay Igorevich
        view client appointments Rodionov Ivan Vladimirovich
        edit client Rodionov Ivan Vladimirovich to Korolev Ivan Vladimirovich
        edit client Korolev Ivan Vladimirovich to Rodionov Ivan Vladimirovich
        delete client Rodionov Ivan Vladimirovich
        delete client lekhmanov Nikolay Igorevich
        delete staff Skitin Artem Mihailovich
        change status 1 to in progress
        change status 2 to canceled
        change status 3 to waiting for payment
        change status 4 to completed
        create note test this is first try to create a note - создаем заметку в текущей директории
        create folder newFolder - создает папку в текущей директориии
        ls -вывод всех папок и заметок в текущей папке
        cd name - зайти в папку name
        view all notes







         */

        Authentication.authenticate();


        CommandReader.startReading();




//        ResultSet resultSet;
//        Note findNote = null;
////        Folder parentFolder = null;
//
//        String select = "SELECT " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
//                ConfigNote.TABLE_NOTE + "." + ConfigNote.NAME_NOTE + ", " +
//                ConfigNote.TABLE_NOTE + "." + ConfigNote.TEXT + ", " +
//                ConfigNote.TABLE_NOTE + "." + ConfigNote.NAME_PARENT_FOLDER +
//                " FROM " + ConfigUsers.USERS_TABLE +
//                " INNER JOIN " + ConfigNote.TABLE_NOTE +
//                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + " = " +
//                ConfigNote.TABLE_NOTE + "." + ConfigNote.USER_ID +
//                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE + "= 'admin'";
//        System.out.println(select);
//
//
//        try{
//            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
//            preparedStatement.setString(1, "admin");
//            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);
//
////            while (resultSet.next()){
////                int idNote = resultSet.getInt(ConfigNote.ID_NOTE);
////                String noteName = resultSet.getString(ConfigNote.NAME_NOTE);
////                String text = resultSet.getString(ConfigNote.TEXT);
////                Date creatingDate = resultSet.getDate(ConfigNote.CREATION_DATE);
////                Date updateDate = resultSet.getDate(ConfigNote.UPDATE_DATE);
////                String nameFolder = resultSet.getString(ConfigNote.NAME_PARENT_FOLDER);
////                String email = resultSet.getString(ConfigNote.EMAIL);
////
//////                Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameFolder) ;
////                findNote = new Note(idNote, nameFolder, noteName, text, email, creatingDate, updateDate);
////            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }

}
