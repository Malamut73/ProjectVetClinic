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
        // edit client Korolev Ivan Vladimirovich to Rodionov Vladimirovich

        // create appointment Rodionov Ivan Vladimirovich to Skitin Artem Mihailovich on 03.07.2022 22:00
        // change status 1 Rodionov Ivan Vladimirovich to in progress
        // change status 2 Rodionov Ivan Vladimirovich to canceled
        // change status 3 Rodionov Ivan Vladimirovich to waiting for payment
        // change status 1 Rodionov Ivan Vladimirovich to completed
        // view appointments Rodionov Ivan Vladimirovich

        //exit


//        Authentication.authenticate();
        CommandReader.startReading();
    }

}
