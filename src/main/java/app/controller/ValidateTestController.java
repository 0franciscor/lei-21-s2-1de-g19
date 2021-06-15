package app.controller;

import app.domain.model.Notification;
import app.domain.model.Test;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * Controller which handles the ValidateTestUI class.
 *
 * @author Francisco Redol
 */
public class ValidateTestController {

    public ValidateTestController(){}

    /**
     * Method responsible for returning Dto objects, which meet the specified criteria.
     *
     * @return a list containing Dto objects which are ready to be validated.
     */
    public List<TestDto> getAllTestsDto(){
        TestStore store = App.getInstance().getCompany().getTestStore();
        List<Test> testsList = store.getDiagnosedTests();
        List<TestDto> testsListDto = TestMapper.toDtoLabC(testsList);
        return testsListDto;
    }

    /**
     * @param code of the Report that the Laboratory Coordinator wants to validate.
     *
     * Method responsible for validating a Report.
     *
     * @return Success of the operation (true if successful and false if not).
     */
    public boolean validateReport(String code){
        ReportStore store = App.getInstance().getCompany().getReportStore();
        return store.validateReport(code);
    }

    /**
     * @param code of the test that the Laboratory Coordinator wants to validate.
     *
     * Method responsible for validating tests.
     *
     * @return Success of the operation (true if successful and false if not).
     */
    public boolean validateTest(String code){
        TestStore store = App.getInstance().getCompany().getTestStore();
        Test test = store.getTestByCode(code);
        return store.validateTest(test);
    }

    /**
     * A method which is able to send a notification to a Client.
     */
    public void sendNotification() throws IOException {
        Notification notificationService = App.getInstance().getCompany().getNotificationService();
        notificationService.writeToFile("Dear Client, your test has been validated. Login on your app to see the results.");

    }

}
