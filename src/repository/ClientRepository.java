package repository;

import moduls.Client;

import java.util.Set;

public interface ClientRepository {

    void saveClient (Client client);
    void removeClient (Client client);
    void editClient (Client client);
    boolean findAll();
    Client getClient(Client client);



}
