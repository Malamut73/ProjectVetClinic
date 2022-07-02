package command.executer;

import command.CommandReader;
import command.CommandType;
import users.Appointment.Appointment;
import users.Appointment.AppointmentType;
import users.Client;
import users.Staff;
import users.User;

import java.util.Optional;

public class AppointmentCreator extends AbstractCommandExecutor{

    @Override
    public int execute(String command) {
        return createAppointment(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_APPOINTMENT;
    }



    private int createAppointment(String command){
        Staff staffForAppointment = null;
        Client lookingUserToCreateAppointment = null;

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;
        //create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 02.07.2022 22:00
        var staffLastName = wordArray[6];
        var staffFirstName = wordArray[7];
        var staffMiddleName = wordArray[8];
        var staffFullName = staffLastName + " " + staffFirstName + " " + staffMiddleName;

        String dateOfAppointment = wordArray[10] + " " + wordArray[11];

        Optional<User> clientCheck = findUser(fullName);
        if(!(clientCheck.isPresent())){
            System.out.println("Client not found");
            return -1;
        }
        Optional<User> staffCheck = findUser(staffFullName);
        if(!(staffCheck.isPresent())){
            System.out.println("Staff not found");
            return -1;
        }

        for (User user :
                userRepository.findAll()) {
            if (user.getFullName().equals(fullName)) {
                lookingUserToCreateAppointment = (Client)user;
//                System.out.println("Клиент кому надо сделать запись найден");
            }
        }
        for (User user :
                userRepository.findAll()) {
            if(user.getFullName().equals(staffFullName)){
                staffForAppointment = (Staff)user;
//                System.out.println("Работник найден");
            }
        }

        String newAppointment = "New appointment";
        Appointment appointment = new Appointment(newAppointment, staffForAppointment, dateOfAppointment, lookingUserToCreateAppointment);
        lookingUserToCreateAppointment.getAppointments().add(appointment);

        System.out.println("Appointment was created");

        for (Appointment str : lookingUserToCreateAppointment.getAppointments()
                ) {
            str.printInfo();
        }

        return 1;
    }

}
