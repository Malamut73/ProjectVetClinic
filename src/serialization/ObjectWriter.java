package serialization;

import repository.UserRepositoryImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectWriter extends Serialization{

    public static void writeObject(){
        UserRepositoryImpl userRepository = UserRepositoryImpl.getSingleton();

        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(userRepository.findAll());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
