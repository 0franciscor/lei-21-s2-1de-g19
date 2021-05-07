package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import auth.AuthFacade;
import auth.domain.model.Password;
import auth.domain.store.ClientStore;
import auth.mappers.dto.ClientDto;

import java.util.Random;

public class RegisterClientController {

    private App app;
    private Company company;
    public ClientStore clientstore;
    private AuthFacade authFacade;

    public RegisterClientController ()
    {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = company.getAuthFacade();
    }

    public Client registerClient (ClientDto dto){

        return company.registerClient(dto);

    }

    public boolean saveClient (Client cl){

        if (getClientstore())
            if (this.clientstore.saveClient(cl)){
                String pwd = generatePwd();
                authFacade.addUser(cl.getEmail(),cl.getName(), pwd);
            }

        return false;

    }

    public boolean getClientstore (){

        this.clientstore = company.getClientStore();

        return true;
    }

    public String generatePwd(){

        Random random = new Random();

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String strAux = "";

        for (int i=0; i<10; i++){

            int x = 1 + random.nextInt(61);
            char aux = str.charAt(x);
            strAux += aux;
        }

        return strAux;
    }
}