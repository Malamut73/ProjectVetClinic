package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import connector.Connector;
import repository.config.ConfigAppointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDeleter extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return null;
    }

    private int deleteAppointment(int numberOfAppointment){

        String select = "DELETE FROM " +
                ConfigAppointment.APPOINTMENT_TABLE +
                " WHERE " +
                ConfigAppointment.ID_APPOINTMENT + " = " +
                numberOfAppointment;

        Statement statement = null;
        try {
            statement = Connector.getConnection().createStatement();
            statement.executeUpdate(select);
            System.out.println("Appointment was deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 1;
    }

}
