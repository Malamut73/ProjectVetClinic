package vetClinic;

import connector.Connector;
import helper.Helper;
import moduls.classes.Staff;
import repository.config.ConfigLogPass;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {


    public static void authenticate() {

        Staff staff = null;

        Scanner scanner = new Scanner(System.in);

        boolean isAuthenticationSuccess = false;
        for (int i = 3; i != 0 && !isAuthenticationSuccess; i--) {
            String lastName;
            String login;
            String password;
            String newPassword;


            System.out.println("Enter your login:");
            login = scanner.nextLine();


            System.out.println("Enter your password:");
            password = scanner.nextLine();

            if(validate(login, password)){
                isAuthenticationSuccess = true;

                if(Helper.getHELPER().isFirstEnter()){
                    System.out.println("You haven't set a password");
                    System.out.println("Please enter a new password");
                    newPassword = scanner.nextLine();
                    setNewPassword(newPassword);
                }else{
                    System.out.println("Login success");
                }
            }else{
                System.out.println("Password is incorrect. Please try again. ");
            }
        }

        if(!isAuthenticationSuccess){
            throw new RuntimeException("Login failed");
        }

    }

    private static void setNewPassword(String newPassword) {

        String select =
                "UPDATE " + ConfigLogPass.LOG_PASS_TABLE +
                        " SET " +
                        ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.PASSWORD + " = ?" +
                        " WHERE " +
                        ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.USER_ID + " = ?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2, Helper.getStaff().getUserId());

            preparedStatement.executeUpdate();
//            System.out.println(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("A password has been set");

    }


    private static boolean validate(String login, String password) {


        Staff staff = null;
        ResultSet resultSet;


        String select = "SELECT " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.LOGIN + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.PASSWORD + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE +
                " FROM " + ConfigUsers.USERS_TABLE +
                " INNER JOIN " + ConfigLogPass.LOG_PASS_TABLE +
                " ON " + ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.USER_ID +
                " = " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER +
                " WHERE " +
                ConfigLogPass.LOGIN + " =? AND " +
                ConfigLogPass.PASSWORD + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                String passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);

//                Helper.getStaff().setAll(userId, lastName, firstName, middleName, loginDB, passwordDB, role);

                staff = new Staff(userId, lastName, firstName, middleName, loginDB, passwordDB, role);
                Helper.setStaff(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(staff == null){
//            System.out.println("Account not found");
            return false;
        }
//        Helper.getHELPER().setROLE(user.getRole());
        if (Helper.getStaff().getPassword().equals("")) {
            Helper.getHELPER().setFirstEnter(true);
            return true;
        } else {

            return ((Helper.getStaff().getLogin().equals(login) || Helper.getHELPER().isFirstEnter())
                    && Helper.getStaff().getPassword().equals(password));
        }
    }

}



