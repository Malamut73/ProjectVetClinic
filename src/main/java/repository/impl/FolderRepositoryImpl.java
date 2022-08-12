package repository.impl;

import connector.Connector;
import moduls.classes.Folder;
import repository.FolderRepository;
import repository.config.ConfigFolder;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New folder was created");
    }

    @Override
    public Folder findFolder(String name) {

        ResultSet resultSet;
        Folder newFolder = null;

        String select = "SELECT * FROM " +
                ConfigFolder.TABLE_FOLDER +
                " WHERE " +
                ConfigFolder.NAME_FOLDER + " =?";
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

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
    public Set<Folder> findFolder(Folder folder) {

        Set<Folder> folders = new HashSet<>();
        ResultSet resultSet;
        Folder newFolder = null;

        String select = "SELECT * FROM " +
                ConfigFolder.TABLE_FOLDER +
                " WHERE " +
                ConfigFolder.PARENT_FOLDER + " =?";
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, folder.getName());
            resultSet = preparedStatement.executeQuery();

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
    public List<String> findFolderPath(String nameFolder){

        List<String> path = new LinkedList<>();
            if(nameFolder.equals("root")){
                path.add(nameFolder);
                return path;
            }
            folderPath(nameFolder, path);
            Collections.reverse(path);
            return path;
    }

    private void folderPath(String nameFolder, List<String> path) {

        ResultSet resultSet = null;
            path.add(nameFolder);


        String select = "SELECT " + ConfigFolder.TABLE_FOLDER + "." + ConfigFolder.PARENT_FOLDER +
                " FROM " + ConfigFolder.TABLE_FOLDER +
                " WHERE " + ConfigFolder.TABLE_FOLDER + "." + ConfigFolder.NAME_FOLDER + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, nameFolder);
            resultSet = preparedStatement.executeQuery();

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




}
