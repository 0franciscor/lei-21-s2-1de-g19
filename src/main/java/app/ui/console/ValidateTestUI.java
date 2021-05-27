package app.ui.console;

import app.controller.ValidateTestController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ParameterCategoryDto;
import auth.mappers.dto.TestDto;
import auth.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

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

            if (!exceptionThrown) {
                List<TestDto> testsListDto = validateTestController.getAllTestsDto();
                if(testsListDto != null && !testsListDto.isEmpty()){

                    for(int i = 0; i < testsListDto.size(); i++){
                        System.out.printf("Test " + (i+1) + " was registered on " + testsListDto.get(i).getRegistrationDateTime()
                        + " , analysed on " + testsListDto.get(i).getChemicalAnalysisDateTime() + " and its diagnose made on " + testsListDto.get(i).getDiagnosisDateTime());
                    }

                    System.out.println("Please choose a test, or all tests. If you want to choose all tests, insert ");


                }
            }


        } while (option != -1);

    }
}
