package command.executer;



import repository.UserRepository;
import repository.UserRepositoryImpl;
import users.User;

import java.io.IOException;
import java.util.Optional;


public abstract class AbstractCommandExecutor implements CommandExecutor {

    protected final UserRepository userRepository = UserRepositoryImpl.getSingleton();

    protected AbstractCommandExecutor(){
    }

    protected Optional<User> findUser(String fullName){
        for (User user :
                userRepository.findAll()) {
            if(user.getFullName().equals(fullName)){
                return Optional.of(user);

            }
        }
        return Optional.empty();
    }




}
