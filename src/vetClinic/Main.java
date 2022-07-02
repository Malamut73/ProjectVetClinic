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

//        Authentication.authenticate();
        CommandReader.startReading();
    }

}
