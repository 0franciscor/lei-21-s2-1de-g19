package app.ui.console;
import app.controller.RecordTestResultController;


public class RecordTestResultUI implements Runnable {
    private RecordTestResultController ctrl;
    public RecordTestResultUI() { ctrl = new RecordTestResultController(); }

    public void run(){
        int exit = 1;
        do {
//            List<Client> clientList = ctrl.getClientList();
//            System.out.println("------ List of Tests available to record results. ------");
//            Client client = (Client) Utils.selectsObject(clientList);
//            String tin = ctrl.getClientTin(client);
//            List<Test> listTestFromClient = ctrl.getTestsByTIN(tin);
//            Test test = (Test) Utils.selectsObject(listTestFromClient);
//            if (test.getStatus().equalsIgnoreCase(Test.Status.Collected.toString())) {
//                for (ParameterResult c : test.getParameterResults()) {
//                    System.out.println("Register the result for this parameter.");
//                    c.toString(2);
//                    String result = Utils.readLineFromConsole("Set the result.");
//                    c.setResult(result);
//                }
//            }
//            System.out.println("Results set with success.");
        } while (exit == 1);
    }
}

