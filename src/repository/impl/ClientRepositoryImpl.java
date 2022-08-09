package repository.impl;

import connector.Connector;
import repository.ClientRepository;
import moduls.classes.Client;
import repository.config.ConfigLogPass;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ClientRepositoryImpl implements ClientRepository {

    private final static Set<Client> CLIENTS = new HashSet<>();
    private final static ClientRepository CLIENT_REPOSITORY = new ClientRepositoryImpl();

    private ClientRepositoryImpl(){}

    public static ClientRepository GET_CLIENT_REPOSITORY_SQL(){

        return CLIENT_REPOSITORY;
    }

    @Override
    public void saveClient(Client client) {

        String insert = "INSERT INTO " + ConfigUsers.USERS_TABLE + " (" +
                ConfigUsers.LASTNAME + ", " +
                ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.MIDDLE_NAME + ", " +
                ConfigUsers.USER_ROLE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            preparedStatement.setString(4, client.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertLogPass =
                "INSERT INTO " + ConfigLogPass.LOG_PASS_TABLE + " (" +
                        ConfigLogPass.LOGIN + ", " +
                        ConfigLogPass.PASSWORD + ", " +
                        ConfigLogPass.USER_ID + ")" +
                        "VALUES(?, ?, ?)";

        Client staffDB = getClient(client);
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insertLogPass);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setInt(3, staffDB.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New client was created");
    }
    @Override
    public void removeClient(Client client) {

        String delete =
                "DELETE FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " = ?";

                ;
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(delete);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println("Client was deleted");
    }
    @Override
    public void editClient(Client client) {

        String select =
                "UPDATE " + ConfigUsers.USERS_TABLE +
                " SET " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " = ?, " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME +  " = ?, " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " = ? " +
                " WHERE " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + " = ?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            preparedStatement.setInt(4, client.getUserId());
            preparedStatement.executeUpdate();
//            System.out.println(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Client was edit");
    }
    @Override
    public boolean findAll() {

        Client client = null;
        ResultSet resultSet;

        String select = "SELECT " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.LOGIN + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.PASSWORD + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE +
                " FROM " + ConfigUsers.USERS_TABLE +
                " INNER JOIN " + ConfigLogPass.LOG_PASS_TABLE +
                " ON " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + " = " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.USER_ID +
                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE +
                " = ?";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, ConfigUsers.USER_TYPE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                String passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                client = new Client(userId, lastName, firstName, middleName, loginDB, passwordDB, role);
                System.out.println(client.toString());
//                CLIENTS.add(new Client(userId, lastName, firstName, middleName, loginDB, passwordDB, role));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return client != null ? true : false ;
    }
    @Override
    public Client getClient(Client client) {

        Client tmpClient = null;
        ResultSet resultSet;

        String selectFIO = "SELECT * FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " +
                ConfigUsers.LASTNAME + " =? AND " +
                ConfigUsers.FIRSTNAME + " =? AND " +
                ConfigUsers.MIDDLE_NAME + " =?";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectFIO);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                tmpClient = new Client(userId, lastName, firstName, middleName, null, null, role);
            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }

        if(!(tmpClient == null)){
            String selectLP = "SELECT * FROM " + ConfigLogPass.LOG_PASS_TABLE +
                    " WHERE " +
                    ConfigLogPass.USER_ID + " =?";
            try {
                PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectLP);
                preparedStatement.setInt(1, tmpClient.getUserId());
                resultSet =  preparedStatement.executeQuery();

                while (resultSet.next()){
                    String loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                    String passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                    tmpClient.setLogin(loginDB);
                    tmpClient.setPassword(passwordDB);
                }
            }catch (SQLException  e) {
                e.printStackTrace();
            }
        }



        return tmpClient;
    }

}
