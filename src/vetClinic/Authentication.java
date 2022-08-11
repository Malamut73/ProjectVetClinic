package vetClinic;

import helper.Helper;
import moduls.classes.User;
import repository.impl.UserRepositoryImpl;

import java.util.Scanner;

public class Authentication {


    public static void authenticate() {

        String login;
        String password;
        String newPassword;
        User user = null;
        var scanner = new Scanner(System.in);
        boolean loginTrue = false;
        boolean passwordTrue = false;
        boolean isAuthenticationSuccess = false;

        for (int i = 3; i != 0 && !isAuthenticationSuccess; i--) {

            System.out.println("Enter your login:");
            login = scanner.nextLine();

            loginTrue = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().loginTrue(login);

            if(Helper.getHELPER().isFirstEnter()){

                System.out.println("You haven't set a password");
                System.out.println("Please enter a new password");

                newPassword = scanner.nextLine();
                UserRepositoryImpl.GET_USER_REPOSITORY_SQL().setNewPassword(newPassword);
                isAuthenticationSuccess = true;
                break;
            }

            System.out.println("Enter your password:");
            password = scanner.nextLine();

            passwordTrue = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().passwordTrue(login, password);

            if(loginTrue && passwordTrue ){
                isAuthenticationSuccess = true;
                System.out.println("Login success");

            }else{
                System.out.println("Password is incorrect. Please try again.");
            }
        }

        if(!isAuthenticationSuccess){
            throw new RuntimeException("Login failed");
        }

    }

}



