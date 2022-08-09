package repository;

import moduls.classes.Client;

public interface ClientRepository {

    void saveClient (Client client);
    void removeClient (Client client);
    void editClient (Client client);
    boolean findAll();
    Client getClient(Client client);



}
