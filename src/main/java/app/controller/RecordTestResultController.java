package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import auth.domain.store.ClientStore;
import auth.domain.store.TestStore;

import java.util.List;

public class RecordTestResultController {
    private Company company;
    private ClientStore clientStore;
    private TestStore testStore;

    public RecordTestResultController() {
        company = App.getInstance().getCompany();
        clientStore = company.getClientStore();
        testStore = company.getTestStore();
    }
    public List<Client> getClientList() {
        return clientStore.getClientList();
    }
    public String getClientTin(Client client) {
        return client.getTIN();
    }
    public List<Test> getTestList() {
        return testStore.SeeList();
    }
    public List<Test> getTestsByTIN(String tin){
        return testStore.getTestByTIN(tin);
    }
}
