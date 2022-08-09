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

//         change status 1 to in progress

        var wordArray = command.split(" ");
        int appointmentNumber = Integer.parseInt(wordArray[2]);

        StringBuilder nameOfStatus = new StringBuilder();
        for (int i = 4; i < wordArray.length; i++) {
            nameOfStatus.append(wordArray[i] + " ");

        }

        Appointment appointment = new Appointment(appointmentNumber, nameOfStatus.toString().trim());
        AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL().editAppointment(appointment);

        return 1;
    }
}
