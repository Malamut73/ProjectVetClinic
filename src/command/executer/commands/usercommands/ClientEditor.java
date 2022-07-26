package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import connector.Connector;
import moduls.Client;
import repository.config.ConfigAppointment;
import repository.config.ConfigClient;
import repository.config.ConfigStaff;
import repository.impl.ClientRepositoryImpl;

import java.sql.SQLException;
import java.sql.Statement;


public class ClientEditor extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return editFullName(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.EDIT_CLIENT;
    }

    private int editFullName(String command) {
        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];

        var newLastName = wordArray[6];
        var newFirstName = wordArray[7];
        var newMiddleName = wordArray[8];

        Client newClient = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().getClient(new Client (lastName, firstName, middleName));

        if(newClient == null){
            System.out.println("None of clients was found");
            return -1;
        }

        String select = " UPDATE " +
                ConfigClient.CLIENT_TABLE +
                " SET " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.LASTNAME + " = " + "'" + newLastName + "'" + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.FIRSTNAME +  " = " + "'" + newFirstName + "'" + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.MIDDLE_NAME + " = " + "'" + newMiddleName + "'" + " " +
                " WHERE " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.ID_CLIENT + " = " + newClient.getUserId();
        System.out.println(select);

        try {
            Statement statement = Connector.getConnection().createStatement();
            statement.executeUpdate(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Client was edit");

        return 1;

    }

}
