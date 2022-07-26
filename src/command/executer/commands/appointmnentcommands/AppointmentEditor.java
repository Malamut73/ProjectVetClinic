package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import connector.Connector;
import repository.config.ConfigAppointment;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentEditor extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return editAppointment(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_APPOINTMENT;
    }

    private int editAppointment(String command){



        var wordArray = command.split(" ");
        int appointmentNumber = Integer.parseInt(wordArray[2]);

        StringBuilder nameOfStatus = new StringBuilder();
        for (int i = 4; i < wordArray.length; i++) {
            nameOfStatus.append(wordArray[i] + " ");

        }

        String select = " UPDATE " +
                ConfigAppointment.APPOINTMENT_TABLE +
                " SET " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.STATUS +
                " = " + "'" + nameOfStatus.toString().trim() + "'" +
                " WHERE " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_APPOINTMENT +
                " = " + appointmentNumber;
        System.out.println(select);

        try {
            Statement statement = Connector.getConnection().createStatement();
            statement.executeUpdate(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Appointment was change");


        return 1;
    }
}
