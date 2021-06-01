package app.ui.console;
import app.controller.RecordTestResultController;
import app.domain.model.Client;
import app.domain.model.ParameterResult;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;

import java.sql.SQLOutput;
import java.util.List;


public class RecordTestResultUI implements Runnable {
    private RecordTestResultController ctrl;
    public RecordTestResultUI() { ctrl = new RecordTestResultController(); }

    public void run(){
        int exit = 1;
        do {
            List<Client> clientList = ctrl.getClientList();
            System.out.println("------ List of Tests available to record results. ------");
            Client client = (Client) Utils.selectsObject(clientList);
            String tin = ctrl.getClientTin(client);
            List<Test> listTestFromClient = ctrl.getTestsByTIN(tin);
            Test test = (Test) Utils.selectsObject(listTestFromClient);
            if (test.getStatus().equalsIgnoreCase(Test.Status.Collected.toString())) {
                for (ParameterResult c : test.getParameterResults()) {
                    System.out.println("Register the result for this parameter.");
                    c.toString(2);
                    String result = Utils.readLineFromConsole("Set the result.");
                    c.setResult(result);
                }
            }
            System.out.println("Results set with success.");
        } while (exit == 1);
    }
}

