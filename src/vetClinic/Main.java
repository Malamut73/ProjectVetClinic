package vetClinic;


import command.CommandReader;
import helper.Helper;
import repository.config.ConfigAppointments;
import repository.config.ConfigUsers;


import java.util.Optional;


public class Main {

    public static void main(String[] args) {

        /*/
        Логин и пароль для входа
        login: admin
        pass: admin

        commands:

        create client lekhmanov Nikolay Igorevich admin
        create client Rodionov Ivan Vladimirovich admin
        create staff Skitin Artem Mihailovich admin
        create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 03.07.2022 22:00
        view clients
        view staff
        view client appointments Rodionov Ivan Vladimirovich
        view client appointments lekhmanov Nikolay Igorevich
        view client appointments Rodionov Ivan Vladimirovich
        edit client Rodionov Ivan Vladimirovich to Korolev Ivan Vladimirovich
        edit client Korolev Ivan Vladimirovich to Rodionov Ivan Vladimirovich
        delete client Rodionov Ivan Vladimirovich
        delete client lekhmanov Nikolay Igorevich
        delete staff Skitin Artem Mihailovich
        change status 1 to in progress
        change status 2 to canceled
        change status 3 to waiting for payment
        change status 4 to completed




        create client ...
        Создает клиента с уникальным id и датой создания, необходимо ввести ФИО, примеры:
        create client lekhmanov Nikolay Igorevich
        create client Rodionov Ivan Vladimirovich

        create staff ...
        создает сотрудника с уникальным id и датой создания, необходимо ввести ФИО логин
        и пароль для авторизации, примеры:
        create staff Skitin Artem Mihailovich admin admin

        view clients - оттображает список клиентов

        view staff - оттображает список пероснала

        view appointments - отображает все встречи


        delete client ...
        Удаляет клиента по ФИО
        delete client Rodionov Ivan Vladimirovich

        edit client ... to ...
        редактирует данные клиента. Нужно ввести ФИО клиента которого нужно отредактировать и новые ФИО, примеры:
        edit client Rodionov Ivan Vladimirovich to Korolev Ivan Vladimirovich
        edit client Korolev Ivan Vladimirovich to Rodionov Ivan Vladimirovich

        create appointment ... to ... on ...
        Создает встречу клиента к врачу, необходимо ввести ФИО клиента и ФИО врача к которому нужно записаться,
        а так же дату и время встречи в формате "dd.MM.yyyy hh:mm", у встречи будет уникальный номер,
        у вновь созданной встречи будет статус новая встреча, примеры:
        create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 03.07.2022 22:00

        view client appointments ...
        отображает встречи определенного клиента, необходимо ввести ФИО клиента
        view client appointments lekhmanov Nikolay Igorevich
        view client appointments Rodionov Ivan Vladimirovich

        change status ... to ...
        меняем статус у встречи, поиск встречи осуществляется по номеру, его можно получить с помощью
        команды view appointment и статус на который хотим поменять
        (in progress, canceled, waiting for payment, completed), примеры:
        change status 1 to in progress
        change status 2 to canceled
        change status 3 to waiting for payment
        change status 4 to completed



         */

//        Authentication.authenticate();
//        if(Helper.getStaff().getRole().equals("admin")) {
//            CommandReader.startReading();
//        }else {
//            System.out.println("Tasks for user action haven't been. \n" +
//                            "Have a good day)");
//        }

        CommandReader.startReading();


//        String select = "SELECT " +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.ID_APPOINTMENT + ", "  +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.DATE_OF_APPOINTMENT  + ", "  +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STATUS +
////                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
////                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
////                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME +
//                " FROM " + ConfigUsers.USERS_TABLE +
//                " INNER JOIN " + ConfigAppointments.APPOINTMENT_TABLE +
//                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER +
//                " = " + ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.CLIENT_ID
////                +
////                " INNER JOIN " + ConfigAppointments.APPOINTMENT_TABLE +
////                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER +
////                " = " + ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STAFF_ID
//                ;
//        System.out.println(select);



    }

}
