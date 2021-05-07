package auth.domain.store;

import app.domain.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientStore {

    List<Client> ClientList;

    public ClientStore (){

        this.ClientList = new ArrayList<>();
    }

    public List<Client> getClientList() {
        return ClientList;
    }

    public boolean saveClient (Client cl){

        if (validateClient(cl) == true){
            ClientList.add(cl);
            return true;
        }
        return false;
    }

    public boolean validateClient (Client cl){

        if (ClientList.contains(cl))
            return false;
        else
            return true;
    }
}
