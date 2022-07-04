package serialization;

import repository.UserRepositoryImpl;
import users.AbstractClient;
import Appointment.Appointment;
import users.Client;
import users.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;

public class ObjectReader extends Serialization {



    public static void readUserRepository(){
        UserRepositoryImpl userRepository = UserRepositoryImpl.getSingleton();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        int newUserNextInt = 1;
        int newAppCountNumber = 1;

        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            HashSet<User> users = (HashSet<User>) objectInputStream.readObject();
            for (User user :
                    users) {
                userRepository.save(user);
                if(user instanceof Client){
                    for (Appointment appointment :
                            ((Client) user).getClientsAppointments()) {

                        newAppCountNumber++;
                    }

                }

                newUserNextInt++;
            }
            AbstractClient.setAppointmentCountNumber(newAppCountNumber);
            AbstractClient.setNextId(newUserNextInt);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
