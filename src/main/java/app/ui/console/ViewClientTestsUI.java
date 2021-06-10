package app.ui.console;

import app.controller.ViewTestsController;
import app.domain.model.TestParameterResult;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;

import java.util.Date;
import java.util.List;

/**
 * Method responsible for making possible for the Client to see his performed tests.
 *
 * @author Francisco Redol
 */
public class ViewClientTestsUI implements Runnable{

    /**
     * The UI's Controller
     */
    private ViewTestsController viewTestsController;

    /**
     * The Client's TIN;
     */
    private String TIN;

    /**
     * Empty constructor.
     */
    public ViewClientTestsUI(String TIN) {
        this.viewTestsController = new ViewTestsController();
        this.TIN = TIN;
    }

    /**
     * Run Method
     */
    public void run(){
        List<TestDto> testList = viewTestsController.getClientTests(TIN);

        if(!testList.isEmpty()) {

            int chosenTest = Utils.showAndSelectIndex(testList, "Please choose a test");

            TestType testType = testList.get(chosenTest).getTestType();
            Date chemicalAnalysisDate = testList.get(chosenTest).getChemicalAnalysisDateTime();
            List<TestParameterResult> testParameterResultList = testList.get(chosenTest).getParameterResults();

            System.out.println("Test Data:");
            showData(testType, chemicalAnalysisDate, testParameterResultList);
        }
        else
            System.out.println("Sorry to inform, but the test results you were looking for haven't come out yet.");
    }

    public void showData(TestType testType, Date chemicalAnalysisDate, List<TestParameterResult> parameterResultList){

        System.out.printf("The test was performed on %s type is described as: %s", chemicalAnalysisDate, testType.getDescription());
        System.out.println("List of Parameter Results:");

        for(TestParameterResult parameterResult : parameterResultList){
            System.out.println(parameterResult.toString());
        }

    }
}
