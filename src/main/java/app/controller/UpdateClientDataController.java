package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import auth.domain.store.ClientStore;
import auth.mappers.ClientMapper;
import auth.mappers.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the controller that serves at intermediary between the UI and the domain layer.
 *
 * @author Eduardo Gonçalves
 */
public class UpdateClientDataController {


    /**
     * The controller's App object.
     */
    private App app;

    /**
     *  The controller's Company.
     */
    private Company company;

    /**
     * The controller's Client store.
     */
    public ClientStore clientstore;

    /**
     * Builds a UpdateClientDataController without receiving parameters.
     */
    public UpdateClientDataController () {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientstore = company.getClientStore();

    }

    /**
     * Returns a client Dto that receives the Tax Identification number as a parameter.
     *
     * @param TIN Tax Identification number
     * @return client Dto that receives the citizen card number as a parameter.
     */
    public Client getClient (String TIN) {

        ClientStore store = company.getClientStore();
        Client cl = store.getClient(TIN);

        return cl;

    }


    public List<String> getDataToUpdate (Client cl){

        ClientStore store = company.getClientStore();
        List<String> listDataToUpdate = store.getDataToUpdate(cl);

        return listDataToUpdate;

    }


}
