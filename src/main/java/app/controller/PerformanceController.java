package app.controller;

import app.domain.model.Company;
import auth.domain.store.ClientStore;
import auth.domain.store.TestStore;

/**
 * Represents the Performance Controller.
 *
 * @author Rita Lello
 */
public class PerformanceController {

    /**
     * The controller's App object.
     */
    private App app;

    /**
     * The controller's Company.
     */
    private Company company;

    /**
     * The controller's Test Store.
     */
    private TestStore testStore;

    /**
     * The controller's Client Store.
     */
    private ClientStore clientStore;

    /**
     * Builds a PerformanceController without receiving parameters.
     */
    public PerformanceController(){
        this.app= App.getInstance();
        this.company=app.getCompany();
        this.testStore=company.getTestStore();
        this.clientStore=company.getClientStore();
    }

    /**
     * Calls the method from client store which returns how many clients there are in the company
     * @return the number of clients
     */
    public int getAllClients(){
        return clientStore.getClientListNumber();
    }

    /**
     * Calls the method from test store which returns how many tests waiting for result there are in the company
     * @return the number of tests waiting for result
     */
    public int getAllTestsWaitResult(){
        return testStore.getCollectedTestsNumber();
    }

    /**
     * Calls the method from test store which returns how many tests waiting for diagnosis there are in the company
     * @return the number of tests waiting for diagnosis
     */
    public int getAllTestsWaitDiagnosis(){
        return testStore.getAnalysedTestsNumber();
    }

    /**
     * Calls the method from test store which returns how many performed tests there are in the company
     * @return the number of performed tests
     */
    public int getAllTestsValidated(){
        return testStore.getValidatedTestsNumber();
    }

}
