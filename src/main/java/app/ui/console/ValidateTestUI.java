package app.ui.console;

import app.controller.ValidateTestController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * UI which interacts with the user and allows it to validate a test.
 *
 * @author Francisco Redol
 */
public class ValidateTestUI implements Runnable {

    /**
     * The UI's controller (Handles events).
     */
    private ValidateTestController validateTestController;

    /**
     * The ValidateTestUI builder, which instantiates the Controller.
     */
    public ValidateTestUI() {
        this.validateTestController = new ValidateTestController();
    }

    /**
     * Method required due to the class implementing the Runnable Interface.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Validate a test", new ShowTextUI("You have chosen to validate a test.")));
        options.add(new MenuItem("See the tests that are waiting to be validated.", new ShowTextUI("You Have chosen to see the test to-be validated.")));
        int option = 0;

//        addTestTemp(); // PROPÓSITO DE TESTE, EXPLICADO NA DECLARAÇÃO DO MÉTODO

        do {
            boolean exceptionThrown = false;

            try {
                option = Utils.showAndSelectIndex(options, "\n\nValidate Test menu:");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if (!exceptionThrown && option == 0) {
                List<TestDto> testsListDto = validateTestController.getAllTestsDto();
                if (testsListDto != null && !testsListDto.isEmpty()) {
                    System.out.println("\nAvailable tests:\n");
                    for (int i = 0; i < testsListDto.size(); i++) {
                        System.out.println("Test (" + (i + 1) + ").\n");
                    }

                    try {

                        String aux = Utils.readLineFromConsole("Please choose a test, or all tests. Choose the number of the test you want. If you want to choose all tests, insert 0.");
                        aux.trim().toLowerCase();

                        String [] auxArray = aux.split(" ");
                        int [] chosenTests = Utils.convertStringArray(auxArray);

                        if(chosenTests.length != 0){
                            if(chosenTests.length == 1 && chosenTests[0] == -1){
                                for (TestDto test : testsListDto) {
                                    System.out.printf("\nTest was registered on " + test.getRegistrationDateTime()
                                            + " , analysed on " + test.getChemicalAnalysisDateTime() + " and its diagnose made on " + test.getDiagnosisDateTime() + "\n");
                                }

                                boolean saveIntention = Utils.confirm("Do you want to validate the following tests? If so, press \"s\" and if not, press \"n\".");

                                if(saveIntention) {
                                    for (TestDto test : testsListDto) {
                                        String code = test.getCode();
                                        executaSave(code);
                                    }
                                } else
                                    System.out.println("No test has been validated. Returning to the Laboratory Coordinator menu in a brief moment.");

                            } else{

                                for(int i = 0; i< chosenTests.length; i++) {
                                    System.out.printf("\nTest was registered on " + testsListDto.get(chosenTests[i]).getRegistrationDateTime()
                                            + " , analysed on " + testsListDto.get(chosenTests[i]).getChemicalAnalysisDateTime() + " and its diagnose made on " + testsListDto.get(chosenTests[i]).getDiagnosisDateTime() + ".\n");

                                    boolean saveIntention = Utils.confirm("Do you want to validate the following test? If so, press \"s\" and if not, press \"n\".");
                                    if (saveIntention) {
                                        String code = testsListDto.get(chosenTests[i]).getCode();
                                        executaSave(code);
                                    } else
                                        System.out.println("No test has been validated. Returning to the menu in a brief moment.");
                                    }
                                }
                        }

                    } catch (Exception e) {
                        System.out.println("There was an error when presenting the selected Test. Hint: You may have inserted a number which does not correspond to a test.");
                    }

                } else {
                    System.out.println("There are no tests to validate at the moment. Come back later!");
                    return;
                }
            }

            if(!exceptionThrown && option == 1) {
                List<TestDto> testsListDto = validateTestController.getAllTestsDto();
                if (testsListDto != null && !testsListDto.isEmpty()) {
                    System.out.println("\nAwaiting Validation:\n");
                    for (int i = 0; i < testsListDto.size(); i++) {
                        System.out.println("Test " + (i + 1) + ".\n");
                    }
                } else {
                    System.out.println("There are no tests to validate at the moment. Come back later!");
                    return;
                }
            }

        } while (option != -1);
    }

    public void executaSave(String code){
        boolean validateReportSuccess, validateTestSuccess = false;

        validateReportSuccess = validateTestController.validateReport(code);

        if(validateReportSuccess)
            validateTestSuccess = validateTestController.validateTest(code);

        if(validateTestSuccess) {
            System.out.println("The selected test has been successfully validated!");
            try {
                validateTestController.sendNotification();
                System.out.println("The notification of the test has been successfully sent!");
            } catch (Exception e){
                System.out.println("There was an error when sending the notification.");
            }
        }
    }

//    public void addTestTemp(){ //APENAS PARA SIMULAR US'S ANTERIORES TENDO EM CONTA QUE A US12 NAO FOI TERMINADA DEVIDO A DESISTÊNCIA
//        List<ParameterCategory> pcList = new ArrayList<>();
//        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
//        pcList.add(pc);
//
//        List<Parameter> pList = new ArrayList<>();
//        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
//        pList.add(parameter1);
//
//        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());
//
//
//        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456789", "123456781234");
//        test.generateCode();
//        test.setStatus(app.domain.model.Test.Status.Reported);
//
//        ReportStore reportStore = App.getInstance().getCompany().getReportStore();
//        reportStore.saveReport("code", test.getCode());
//
//        TestStore testStore = App.getInstance().getCompany().getTestStore();
//        testStore.saveTest(test);
//
//
//
//
//        app.domain.model.Test test2 = new app.domain.model.Test(testType, pList, pcList, "1223456789", "123456781234");
//        test2.generateCode();
//        test2.setStatus(app.domain.model.Test.Status.Reported);
//
//        reportStore.saveReport("codee", test2.getCode());
//
//        testStore.saveTest(test2);
//
//    }
}




