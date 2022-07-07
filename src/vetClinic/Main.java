package vetClinic;


import command.CommandReader;
import serialization.ObjectReader;

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

        change status ... ... to ...
        меняем статус у встречи, поиск встречи осуществляется по номеру, его можно получить с помощью
        команды view appointment, так же необходимо ввести ФИО клиенты и статус на который хотим поменять
        (in progress, canceled, waiting for payment, completed), примеры:
        change status 1 Rodionov Ivan Vladimirovich to in progress
        change status 2 Rodionov Ivan Vladimirovich to canceled
        change status 3 Rodionov Ivan Vladimirovich to waiting for payment
        change status 4 Rodionov Ivan Vladimirovich to completed

        view appointments ...
        отображает встречи определенного клиента, необходимо ввести ФИО клиента
        view appointments Rodionov Ivan Vladimirovich

        exit
        Осуществляет выход из программы предварительно сохранив данный о созданных сотрудниках
        клиентах и встречах

         */



        ObjectReader.readUserRepository();   // загрузка данный из файла в UserRepository
        Authentication.authenticate();
        CommandReader.startReading();
    }

}
