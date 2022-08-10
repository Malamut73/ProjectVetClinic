package repository;

import moduls.classes.Client;

import java.util.List;

public interface ClientRepository {

    void saveClient (Client client);
    void removeClient (Client client);
    void editClient (Client client);
    List<Client> findAll();
    Client findClient(Client client);
    void saveLogAndPass(Client client);



}
