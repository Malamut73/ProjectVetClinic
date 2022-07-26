package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.*;

import java.util.Optional;

public class AppointmentViewer extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return viewAppointments(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_ALL_APPOINTMENTS;
    }

    private int viewAppointments(String command){

        for (Appointment appointment :
                appointmentRepository.findAll()) {
            System.out.println(appointment.printInfo());
        }

        return 1;
    }
}
