package auth.domain.store;

import app.domain.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Client store class.
 *
 * @author Eduardo Gonçalves
 */
public class ClientStore {

    /**
     * List of clients.
     */
    List<Client> ClientList;

    /**
     * Builds a ClientStore without receiving parameters.
     */

    public ClientStore (){

        this.ClientList = new ArrayList<>();
    }

    /**
     * Returns a list of clients.
     *
     * @return list of clients
     */
    public List<Client> getClientList() {
        return ClientList;
    }

    /**
     * If the client does not belong to the client list adds it to that list and returns true, otherwise returns false.
     *
     * @param cl client to validate
     * @return true if the client is not already on the client list, otherwise returns false.
     */
    public boolean saveClient (Client cl){

        if (validateClient(cl) == true){
            addClient(cl);
            return true;
        }
        return false;
    }

    /**
     * Add a client to the client list.
     *
     * @param cl client to add
     */
    public void addClient (Client cl){

         ClientList.add(cl);

    }

    /**
     * If the client list contains the client returns false, otherwise returns true.
     *
     * @param cl client to check
     * @return false if the client belongs to the client list, otherwise returns true.
     */
    public boolean validateClient (Client cl){

        if (ClientList.contains(cl))
            return false;
        else
            return true;
    }



    public Client getClient (String citizenID) {

        for (Client c : ClientList ) {

            if (citizenID.equals(c.getCitizenID()))
                return c;

        }
        return null;
    }


}
