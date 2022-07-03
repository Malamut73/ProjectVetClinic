package serialization;

import repository.UserRepositoryImpl;
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


        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);

            HashSet<User> users = (HashSet<User>) objectInputStream.readObject();
            for (User user :
                    users) {
                userRepository.save(user);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
