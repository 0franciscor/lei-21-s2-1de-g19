package app.controller;

import app.domain.model.BruteForceAlgorithm;
import app.domain.model.Company;
import auth.domain.store.ClientStore;
import auth.domain.store.TestStore;
import com.isep.mdis.Sum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
     * The controller's sum.
     */
    private Sum sum;

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
     * Calls the method from test store which returns how many tests waiting for result there are in the company in the asked interval
     * @return the number of tests waiting for result
     */
    public int getAllTestsWaitResult(Date beginning, Date end){
        return testStore.getCollectedTestsNumber(beginning, end);
    }

    /**
     * Calls the method from test store which returns how many tests waiting for diagnosis there are in the company in the asked interval
     * @return the number of tests waiting for diagnosis
     */
    public int getAllTestsWaitDiagnosis(Date beginning, Date end){
        return testStore.getAnalysedTestsNumber(beginning, end);
    }

    /**
     * Calls the method from test store which returns how many tests there are in the company in the asked interval
     * @return the number of performed tests
     */
    public int getAllTestsValidated(Date beginning, Date end){
        return testStore.getValidatedTestsNumber(beginning, end);
    }

    /**
     * Calls the method from the sum class that calculates the subsequence
     * @param seq
     * @return the contiguous subsequence
     */
    public int [] getSubsequenceBenchmark(int[] seq){
        return sum.Max(seq);
    }

    /**
     * Calls the method from the BruteForce class that calculates the subsequence
     * @param seq
     * @return the contiguous subsequence
     */
    public int [] getSubsequenceBruteForce(int[] seq){
        return BruteForceAlgorithm.Max(seq);
    }

}
