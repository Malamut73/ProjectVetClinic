package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Appointment;
import moduls.classes.Client;

import java.util.List;

public class ClientAppointmentViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewClientAppointments(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_CLIENT_APPOINTMENTS;
    }

    private int viewClientAppointments (String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[3];
        var firstName = wordArray[4];
        var middleName = wordArray[5];


        Client client = clientRepository.findClient(new Client(lastName, firstName, middleName));


        if(client == null){
            System.out.println("Client not found");
            return -1;
        }else{
            List<Appointment> appointments = appointmentRepository.getClientAppointments(client);
            if(appointments.isEmpty()){
                System.out.println("Any appointments was found");
                return -1;
            }else{
                for (Appointment appointment :
                        appointmentRepository.getClientAppointments(client)) {
                    System.out.println(appointment.printInfo());
                }
            }
        }
        return 1;
    }
}
