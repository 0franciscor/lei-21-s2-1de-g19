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

    private ValidateTestController validateTestController;

    public ValidateTestUI() {
        this.validateTestController = new ValidateTestController();
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Validate a test", new ShowTextUI("You have chosen to validate a test.")));

        int option = 0;


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

                    for (int i = 0; i < testsListDto.size(); i++) {
                        System.out.println("Test " + (i + 1));
                    }

                    try {

                        String aux = Utils.readLineFromConsole("\nPlease choose a test, or all tests. Choose the number of the test you want. If you want to choose all tests, insert 0");
                        aux.trim().toLowerCase();

                        String [] auxArray = aux.split(" ");
                        int [] chosenTests = Utils.convertStringArray(auxArray);

                        if(chosenTests.length == 1 && chosenTests[0] == -1){
                            for (TestDto test : testsListDto) {
                                System.out.printf("\nTest was registered on " + test.getRegistrationDateTime()
                                        + " , analysed on " + test.getChemicalAnalysisDateTime() + " and its diagnose made on " + test.getDiagnosisDateTime() + "\n");
                            }

                            boolean saveIntention = Utils.confirm("Do you want to validate the following test? If so, press \"s\" and if not, press \"n\"");

                            if(saveIntention) {
                                for (TestDto test : testsListDto) {
                                    String code = test.getCode();
                                    executaSave(code);
                                }
                            } else
                                System.out.println("No test has been validated. Returning to the Laboratory Coordinator menu in a brief moment.");

                        }

                        if (chosenTests.length != 1) {
                            for(int i = 0; i< chosenTests.length; i++) {
                                System.out.printf("\nTest was registered on " + testsListDto.get(chosenTests[i]).getRegistrationDateTime()
                                        + " , analysed on " + testsListDto.get(chosenTests[i]).getChemicalAnalysisDateTime() + " and its diagnose made on " + testsListDto.get(chosenTests[i]).getDiagnosisDateTime() + "\n");

                                boolean saveIntention = Utils.confirm("Do you want to validate the following test? If so, press \"s\" and if not, press \"n\"");
                                if (saveIntention) {
                                    String code = testsListDto.get(chosenTests[i]).getCode();
                                    executaSave(code);
                                } else
                                    System.out.println("No test has been validated. Returning to the Laboratory Coordinator menu in a brief moment.");
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
        } while (option != -1);
    }

    public void executaSave(String code){
        boolean validateReportSuccess, validateTestSuccess = false;

        validateReportSuccess = validateTestController.validateReport(code);

        if(validateReportSuccess)
            validateTestSuccess = validateTestController.validateTest(code);

        if(validateTestSuccess)
            if(Utils.confirm("Do you want to send a notification to the client? If so, press \"s\" and if not, press \"n\""))
                validateTestController.sendNotification();

        if(validateReportSuccess && validateTestSuccess)
            System.out.println("The test has been successfully validated!");
    }
}




