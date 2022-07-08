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
        int newUserNextInt = 2;
        int newAppCountNumber = 1;


        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            if(fileInputStream.available() > 0) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    HashSet<User> users = (HashSet<User>) objectInputStream.readObject();
                    for (User user :
                            users) {
                        userRepository.save(user);
                        if (user instanceof Client) {
                            for (Appointment appointment :
                                    ((Client) user).getClientsAppointments()) {

                                newAppCountNumber++;
                            }

                        }

                        newUserNextInt++;
                    }
                }
            }
            AbstractClient.setAppointmentCountNumber(newAppCountNumber);
            User.setNextId(newUserNextInt);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
