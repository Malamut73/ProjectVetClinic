package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Appointment;
import repository.impl.AppointmentRepositoryImpl;

public class AppointmentStatusChanger extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return editAppointment(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.EDIT_APPOINTMENT;
    }

    private int editAppointment(String command){

        var wordArray = command.split(" ");
        int appointmentId = Integer.parseInt(wordArray[2]);

        StringBuilder statusBuilder = new StringBuilder();
        for (int i = 4; i < wordArray.length; i++) {
            statusBuilder.append(wordArray[i] + " ");
        }
        String status = statusBuilder.toString().trim();
        Appointment appointment = new Appointment();
        appointment.setIdAppointment(appointmentId);
        appointment.setStatus(status);

        AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL().editAppointment(appointment);

        return 1;
    }
}
