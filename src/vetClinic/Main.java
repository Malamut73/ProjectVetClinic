package vetClinic;


import command.CommandReader;


public class Main {

    public static void main(String[] args) {

        /*/
        Логин и пароль для входа
        login: admin
        pass: admin

        commands:

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

        change status ... to ...
        меняем статус у встречи, поиск встречи осуществляется по номеру, его можно получить с помощью
        команды view appointment и статус на который хотим поменять
        (in progress, canceled, waiting for payment, completed), примеры:
        change status 1 to in progress
        change status 2 to canceled
        change status 3 to waiting for payment
        change status 4 to completed

        exit
        Осуществляет выход из программы предварительно сохранив данный о созданных сотрудниках
        клиентах и встречах

         */


          CommandReader.startReading();

//        var user = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().getClient(new Client("lekhmanov", "Nikolay", "Igorevich"));
//        System.out.println(AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL().getAppointment(user).printInfo());


    }

}
