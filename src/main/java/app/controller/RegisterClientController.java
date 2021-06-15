package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.OrgRole;
import auth.AuthFacade;
import auth.domain.store.ClientStore;
import auth.mappers.dto.ClientDto;

import java.io.IOException;

/**
 * Represents the controller that serves at intermediary between the UI and the domain layer.
 *
 * @author Eduardo Gonçalves
 */
public class RegisterClientController {

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
     * The controller's AuthFacade.
     */
    private AuthFacade authFacade;

    /**
     * Builds a RegisterClientController without receiving parameters.
     */
    public RegisterClientController ()
    {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = company.getAuthFacade();
        this.clientstore = company.getClientStore();
    }

    /**
     * Returns the method register client from the company that receives the client's dto as a parameter.
     *
     * @param dto Client's dto
     * @return method register client from the company that receives the client's dto as a parameter.
     */
    public Client registerClient (ClientDto dto){

        return company.registerClient(dto);
    }

    /**
     * If the saveClient method of the client store is true then a password is generated and the addUser method that receives
     * the client's name, client's email and password is called and returns true, otherwise returns false.
     *
     * @return true if the saveClient method of the client store is true, otherwise return false
     */
    public boolean saveClient (Client cl) throws IOException {

        if (this.clientstore.saveClient(cl)){
            String pwd = cl.generatePwd();
            cl.sendNotification();

            authFacade.addUserWithRole(cl.getName(),cl.getEmail(), pwd, "CLIENT");
            return true;
        }
        return false;
    }

    /**
     * Returns the client store.
     *
     * @return client store
     */
    public ClientStore getClientstore (){

        return this.clientstore;
    }
}