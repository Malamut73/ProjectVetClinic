package vetClinic;


import command.CommandReader;
import connector.Connector;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import repository.config.ConfigFolder;
import repository.config.ConfigLogPass;
import repository.config.ConfigNote;
import repository.config.ConfigUsers;
import repository.impl.FolderRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Main {

    public static void main(String[] args) {

        /*/
        Логин и пароль для входа
        login: admin1
        pass: 1234
Filipov Aleksey Mihailovich 2022-08-08 16:31

        commands:
        create client lekhmanov Nikolay Igorevich blabla@mail.ru - создаем клиента командой create client так же добавляем ФИО и логин.
        create staff Skitin Artem Mihailovich blabla@mail.ru     - создаем сотрудника командой create staff так же добавляем ФИО и логин.
                                                                   для входы в первый раз нужно будет ввести логин который был указана при создании юзера

        create appointment lekhmanov Nikolay Igorevich to Filipov Aleksey Mihailovich on 08.08.2202 16:31
        create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 03.07.2022 22:00 -  создаем запсь на прием команндой create appointment,
                                                                    так же нужно ввести ФИО клиента и ФИО сотрудника которых мы хотим записать
        view clients    - выведит список всех клиентов
        view staffs     - выведит список всех сотрудиков
        view client appointments lekhmanov Nikolay Igorevich       - выводим все записи к варчу определенного клиента командой view client appointments,
                                                                    так же необходимо указать ФИО клиента
        edit user Rodionov Ivan Vladimirovich to Korolev Ivan Vladimirovich - редактируем пользователя командой edit user ... to ..., так же необходимо ввести
                                                                                ФИО клиента кторого хоитм отредактировать и новое ФИО
        delete user Rodionov Ivan Vladimirovich                               - удаляем юзера командой delete user , так же необходимо ввести ФИО юзера
        change status 1 to in progress/canceled/waiting for payment/completed - меняем статус встречи командой change status ... to ...
                                                                                необходимо ввести уникальный номер встречи и новый статус

        create note test this is first try to create a note                   - создаем заметку в текущей директории
        create folder newFolder                                               - создает папку в текущей директориии
        ls                                                                    - вывод всех папок и заметок в текущей папке
        cd name                                                               - зайти в папку "name"
        view all notes                                                        - выводим все заметки
        view notes in firdFolder                                              - показываем все заметки в папке "firdFolder" текущего юзера
         */

        Authentication.authenticate();
        if(Helper.getUser().getRole().equals("staff")){
            CommandReader.startReading();
        }else{
            System.out.println("There are not commands for clients.");
        }

    }

}
