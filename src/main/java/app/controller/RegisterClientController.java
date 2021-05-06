package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import auth.mappers.dto.ClientDto;

public class RegisterClientController {

    private App app;

    public RegisterClientController ()
    {
        this.app = App.getInstance();
    }

    public Client registerClient (ClientDto dto){

        Company company = app.getCompany();

        return company.registerClient(dto);

    }
}
