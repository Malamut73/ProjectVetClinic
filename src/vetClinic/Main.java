package vetClinic;

import java.util.HashMap;

public class Main {

    public static HashMap<Integer, Client> clients = new HashMap<>();


    public static void main(String[] args) {

        Client cl0 = Client.createClient("Skitin", "Artem", "Mihailovich");
        Client cl1 = new Client("Lekhmanov", "Nikolai", "Igorevich");
        Client cl2 = new Client("Rodionov", "Ivan", "Vladimirovich");
        cl1.putToClients(clients);
        cl2.putToClients(clients);
        System.out.println(cl0);
        for (Client client :
                clients.values()) {
            System.out.println(client.toString());
        }
    }
}
