package repository.impl;

import connector.Connector;
import repository.ClientRepository;
import repository.config.ConfigClient;
import moduls.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

        String insert = "INSERT INTO " + ConfigClient.CLIENT_TABLE + "(" +
                ConfigClient.LASTNAME + "," + ConfigClient.FIRSTNAME + "," +
                ConfigClient.MIDDLE_NAME + ")" +
                "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            preparedStatement.executeUpdate();
            System.out.println("New client was created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeClient(Client client) {
    }
    @Override
    public void editClient(Client client) {
    }
    @Override
    public Set<Client> findAll() {

        String select = "SELECT * FROM client";

        try{
            PreparedStatement preparedStatement = Connector.getConnection()
                    .prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                int userId = resultSet.getInt(ConfigClient.ID_CLIENT);
                String firstName = resultSet.getString(ConfigClient.FIRSTNAME);
                String lastName = resultSet.getString(ConfigClient.LASTNAME);
                String middleName = resultSet.getString(ConfigClient.MIDDLE_NAME);
                Date dateOfRegistration = resultSet.getDate(ConfigClient.DATE_OF_REGISTRATION);

                CLIENTS.add(new Client(userId, lastName, firstName, middleName, dateOfRegistration));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return CLIENTS;
    }

    @Override
    public Client getClient(Client client) {

        Client tmpClient = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigClient.CLIENT_TABLE +
                " WHERE " +
                ConfigClient.LASTNAME + " =? AND " +
                ConfigClient.FIRSTNAME + " =? AND " +
                ConfigClient.MIDDLE_NAME + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getMiddleName());
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigClient.ID_CLIENT);
                String firstName = resultSet.getString(ConfigClient.FIRSTNAME);
                String lastName = resultSet.getString(ConfigClient.LASTNAME);
                String middleName = resultSet.getString(ConfigClient.MIDDLE_NAME);
                Date dateOfRegistration = resultSet.getDate(ConfigClient.DATE_OF_REGISTRATION);
                tmpClient = new Client(userId, lastName, firstName, middleName, dateOfRegistration);

            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }
        if(!(client.equals(tmpClient))){
            System.out.println("Client already exist");
            return tmpClient = null;

        }
        return tmpClient;
    }

}
