package app.ui.console;

import app.controller.ConsultTestDetailsByClientController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.TestDto;

import java.sql.SQLOutput;
import java.util.List;

public class ConsultTestDetailsByClientUI implements Runnable {
    private ConsultTestDetailsByClientController ctrl;
    public ConsultTestDetailsByClientUI() { ctrl = new ConsultTestDetailsByClientController(); }

    public void run(){
        try {
            boolean c = false;
            String criteria;
            do {
                criteria = Utils.readLineFromConsole("Choose an order criteria to order the client list. \n 1- Name \n 2- TIN");
                if (criteria.equalsIgnoreCase("1"))
                    criteria = "Name";
                c = true;
                if (criteria.equalsIgnoreCase("2"))
                    criteria = "TIN";
                    c = true;
            } while (c == false);
            List<ClientDto> listClient = ctrl.orderClients(criteria);
            ClientDto clientDto = (ClientDto) Utils.showAndSelectOne(listClient, "Select a client to view his historical tests." );
            List<TestDto> listTest = ctrl.presentTestsOfClient(clientDto.getTIN());
            TestDto test = (TestDto) Utils.showAndSelectOne(listTest, "Select a test to view its details.");
            ctrl.presentTestDetails(test.getCode());

        } catch (Exception E) {
            System.out.println("There was an error.");
            return;
        }
    }
}
