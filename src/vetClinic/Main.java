package vetClinic;


import command.CommandReader;

public class Main {

    public static void main(String[] args) {
        // create client lekhmanov Nikolay Igorevich
        // create client Rodionov Ivan Vladimirovich
        // create staff Skitin Artem Mihailovich
        // view clients
        // delete client Rodionov Ivan Vladimirovich
        // edit client Rodionov Ivan Vladimirovich to Korolev Ivan Vladimirovich
        // create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 02.07.2022 22:00


//        Authentication.authenticate();
        CommandReader.startReading();
    }

}
