package auth.domain.store;

import app.domain.model.Client;
import app.domain.model.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Client store class.
 *
 * @author Eduardo Gonçalves
 */
public class ClientStore implements Serializable {

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
            guardarFicheiroBinario(ClientList);
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

    /**
     * If there is a client with the citizen card number equal to what is passed by parameter returns his data, otherwise returns null.
     *
     * @param TIN Client's Tax Identification number
     * @return Client's data if there is a client with the citizen card number equal to what is passed by parameter, otherwise returns null
     */
    public Client getClient (String TIN) {

        for (Client c : ClientList ) {

            if (TIN.equals(c.getTIN()))
                return c;

        }
        return null;
    }

    public List<String> getDataToUpdate(Client client) {

        List<String> listDataToUpdate = new ArrayList<>();

        String sex = client.getSex();
        String phoneNumber = client.getPhoneNumber();
        String email = client.getEmail();
        String name = client.getName();

        listDataToUpdate.add(sex);
        listDataToUpdate.add(phoneNumber);
        listDataToUpdate.add(email);
        listDataToUpdate.add(name);

        return listDataToUpdate;
    }

    /**
     * By calling this method you know how many clients the company has
     *
     * @return the number of clients
     */
    public int getClientListNumber(){
        return(ClientList.size());
    }
    public boolean guardarFicheiroBinario(List<Client> clientList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("clientStore.bin"));
            try {
                for (Client c : clientList)
                    out.writeObject(c);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }
}
